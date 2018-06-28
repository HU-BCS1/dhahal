package com.bcs1.hu.dhaxal

object Ayeeyo: Relative("Ayeeyo", gender = Gender.Female) {

    override fun cut(relatives: Relatives): Cut {
        val (hooyo) = getRelative(relatives, "Hooyo")

        if(hooyo.count == 1) {
            return Cut.Nothing
        }

        return Cut.Sixth
    }
}