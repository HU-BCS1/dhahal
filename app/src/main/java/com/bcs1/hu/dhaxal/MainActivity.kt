package com.bcs1.hu.dhaxal

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.piechart as pieChart


typealias Relatives = Map<String, Relative>

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    var asaba : Relatives = mapOf()
    val entries = mutableListOf<PieEntry>()

    val relatives = mapOf(
            Wiil.name to Wiil,
            Gabadh.name to Gabadh,

            WWiilka.name to WWiilka,
            GWiilka.name to GWiilka,

            Aabo.name to Aabo,
            Hooyo.name to Hooyo,

            Awoowe.name to Awoowe,
            Ayeeyo.name to Ayeeyo,

            Nin.name to Nin,
            Xaas.name to Xaas,

            WalRag.name to WalRag,
            WalDumar.name to WalDumar,
            WalRagAabo.name to WalRagAabo,
            WalDumarAabo.name to WalDumarAabo,
            WalHooyo.name to WalHooyo,

            WWalRag.name to WWalRag,
            WWalRagAabo.name to WWalRagAabo,

            Adeer.name to Adeer,
            AdeerAabo.name to AdeerAabo,

            WAdeer.name to WAdeer,
            WAdeerAabo.name to WAdeerAabo
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pieChart.setEntryLabelColor(Color.DKGRAY)
        pieChart.setEntryLabelTextSize(18f)

        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false

        pieChart.transparentCircleRadius = 50f
        pieChart.holeRadius = 40f

        pieChart.setExtraOffsets(8f, 0f, 8f, 8f)

        pieChart.setUsePercentValues(true)

        updateChart()

        findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MyAdapter(relatives) { id, type ->

                if(id == "Nin" && type == "ADD") {
                    if(relatives.getValue("Xaas").count > 0) return@MyAdapter
                }
                if(id == "Xaas" && type == "ADD") {
                    if(relatives.getValue("Nin").count > 0) return@MyAdapter
                }

                when (type) {
                    "ADD" -> relatives.getValue(id).add(relatives)
                    "SUBTRACT" -> relatives.getValue(id).remove()
                    else -> throw IllegalArgumentException()
                }
                adapter.notifyDataSetChanged()
                updateChart()
            }
        }

        pieChart.setOnChartValueSelectedListener(object: OnChartValueSelectedListener {
            override fun onNothingSelected() = Unit

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if((e as PieEntry).label != "Casaba") return

                val context = this@MainActivity
                val v = layoutInflater.inflate(R.layout.dialog_asaba, null)

                v.findViewById<RecyclerView>(R.id.list).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = DialogAdapter(ArrayList(calculateAsaba()))
                }

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Casaba")
                        .setView(v)
                        .setPositiveButton("ok") { _, _ -> pieChart.highlightValue(null) }
                        .setOnDismissListener { pieChart.highlightValue(null) }
                        .show()
            }
        })

    }

    private fun calculateAsaba() : List<AsabaCut> {
        val males = asaba.values.filter { it.gender == Gender.Male }
        val females = asaba.values.filter { it.gender == Gender.Female }

        val maleCount = males.sumBy { it.count }
        val femaleCount = females.sumBy { it.count }

        var numberCuts = 0

        val (maleCuts, femaleCuts) =
            if(maleCount == 0 || femaleCount == 0) {
                numberCuts = maleCount + femaleCount
                Pair(males.map { AsabaCut(it.name, 1f/ numberCuts) },
                    females.map { AsabaCut(it.name, 1f/ numberCuts) })
            }
            else {
                numberCuts = maleCount + (2 * femaleCount)
                Pair(males.map { AsabaCut(it.name, 2f/ numberCuts) },
                    females.map { AsabaCut(it.name, 1f/ numberCuts) })
            }

        var base = 100f
        entries.filter { it.label == "Casaba" }
               .forEach { base = it.value * 100 }

        return  (maleCuts + femaleCuts).map { it.cut *= base; it }
    }

    private fun updateChart() {
        entries.clear()
        asaba = mapOf()

        relatives.forEach{(key, value) ->
            if(value.count == 0)  return@forEach

            val cut = value.cut(relatives)
            when(cut) {
                Cut.Nothing -> Unit
                Cut.Asaba -> asaba += Pair(key, value)
                Cut.SixthAndAsaba -> {
                    asaba += Pair(key, value)
                    entries.add(PieEntry((Cut.Sixth.value).toFloat(), key))
                }
                else ->  entries.add(PieEntry((cut.value).toFloat(), key))
            }
        }
        val sum = entries.sumByDouble { it.value.toDouble() }
        val remaining = 1 - sum

        when {
            (remaining <= 0) -> entries.forEach { it.value / sum }
            else -> entries.add(PieEntry(remaining.toFloat(), "Casaba"))
        }


        val set = PieDataSet(entries, "Qaybin Dhaxal")
        set.colors = ColorTemplate.LIBERTY_COLORS.toList()

        val data = PieData(set)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(24f)
        data.setValueTextColor(Color.DKGRAY)
        pieChart.data = data
        pieChart.invalidate()
        pieChart.animateY(300)
    }


}

class AsabaCut(val name: String, var cut: Float)

class DialogAdapter(private val myDataset: ArrayList<AsabaCut>) : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtView1 : TextView = v.findViewById(R.id.textView1)
        val txtView2 : TextView = v.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogAdapter.ViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item_2, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        holder.txtView1.text = myDataset[position].name
        holder.txtView2.text = String.format("%.2f %%", myDataset[position].cut)
    }

    override fun getItemCount() = myDataset.size

    companion object {
        const val TAG = "DialogAdapter"
    }
}
