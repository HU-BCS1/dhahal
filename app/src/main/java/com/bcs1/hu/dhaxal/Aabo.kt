package com.bcs1.hu.dhaxal

object Aabo: Relative("Aabo", max = 1) {

    override fun cut(relatives: Relatives): Cut {
        val (wiil, gabadh, wiilka_wiilka, gabadha_wiilka)
                = getRelative(relatives, "Wiil", "Gabadh", "Wiilka Wiilka", "Gabadha Wiilka")

        if(wiil.count > 0 || wiilka_wiilka.count > 0) {
            return Cut.Sixth
        }

        if(gabadh.count > 0 || gabadha_wiilka.count > 0) {
            return Cut.SixthAndAsaba
        }

        return Cut.Asaba
    }
}