package com.bcs1.hu.dhaxal

object GWiilka: Relative("Gabadha Wiilka", gender = Gender.Female) {

    override fun cut(relatives: Relatives): Cut {
        val (wiil, gabadh, wiilka_wiilka)
                = getRelative(relatives, "Wiil", "Gabadh", "Wiilka Wiilka")

        if(wiilka_wiilka.count > 0 && wiil.count == 0) {
            return Cut.Asaba
        }

        if(wiilka_wiilka.count == 0 && wiil.count == 0 && gabadh.count == 0) {
            if(this.count == 1) {
                return Cut.Half
            } else {
                return Cut.TwoThird
            }
        }

        if(wiilka_wiilka.count == 0 && wiil.count == 0 && gabadh.count == 1) {
            return Cut.Sixth
        }

        return Cut.Nothing
    }
}