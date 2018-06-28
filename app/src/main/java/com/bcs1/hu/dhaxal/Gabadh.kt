package com.bcs1.hu.dhaxal

object Gabadh : Relative("Gabadh", gender = Gender.Female) {

    override fun cut(relatives: Relatives) : Cut {

        val (wiil) = getRelative(relatives, "Wiil")

        if(wiil.count > 0) {
            return Cut.Asaba
        }

        return if(count == 1) {
            Cut.Half
        }
        else {
            Cut.TwoThird
        }
    }
}