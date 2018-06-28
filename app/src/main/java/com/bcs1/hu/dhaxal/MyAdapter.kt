package com.bcs1.hu.dhaxal

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class MyAdapter(private val myDataset: Relatives, private val listener: AdapterClickListener) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private val names = myDataset.keys.toList()

    class ViewHolder(v: View, listener: AdapterClickListener) : RecyclerView.ViewHolder(v) {
        val person : TextView = v.findViewById(R.id.textView)
        val count : TextView = v.findViewById(R.id.textView2)
        val minus : ImageButton = v.findViewById(R.id.minus)
        val plus : ImageButton = v.findViewById(R.id.plus)
        lateinit var id : String

        init {
            minus.setOnClickListener { listener(id, "SUBTRACT") }
            plus.setOnClickListener { listener(id, "ADD") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item_1, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id = getItemName(position)
        holder.person.text = getItemName(position)
        holder.count.text = getItem(position).count.toString()
    }

    override fun getItemCount() = myDataset.size

    private fun getItem(position: Int) = myDataset.getValue(names[position])

    private fun getItemName(position: Int) = names[position]

    companion object {
        const val TAG = "MyAdapter"
    }
}

typealias AdapterClickListener = (id: String, type: String) -> Unit