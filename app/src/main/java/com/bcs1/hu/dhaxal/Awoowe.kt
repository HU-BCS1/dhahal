package com.bcs1.hu.dhaxal

object Awoowe: Relative("Awoowe") {

    override fun cut(relatives: Relatives): Cut {
        val (aabo, wiil, gabadh, wiilka_wiilka, gabadha_wiilka)
                = getRelative(relatives,  "Aabo","Wiil", "Gabadh", "Wiilka Wiilka", "Gabadha Wiilka")

        if(aabo.count != 0) {
            return Cut.Nothing
        }

        if(wiil.count > 0 || wiilka_wiilka.count > 0) {
            return Cut.Sixth
        }

        if(gabadh.count > 0 || gabadha_wiilka.count > 0) {
            return Cut.SixthAndAsaba
        }

        return Cut.Asaba
    }
}