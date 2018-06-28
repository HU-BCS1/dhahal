package com.bcs1.hu.dhaxal

object WWiilka: Relative("Wiilka Wiilka") {
    override fun cut(relatives: Relatives): Cut {
        val (wiil) = getRelative(relatives, "Wiil")

        if(wiil.count > 0) return Cut.Nothing

        return Cut.Asaba
    }
}