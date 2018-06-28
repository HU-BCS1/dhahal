package com.bcs1.hu.dhaxal

sealed class Cut(val name: String, val value: Double) {

    override fun toString(): String {
        return name
    }

    object Half : Cut("1/2", 0.5)
    object Quarter : Cut("1/4", 0.25)
    object Eighth : Cut("1/8", 0.125)
    object Third : Cut("1/3", 0.333)
    object TwoThird : Cut("2/3", 0.667)
    object Sixth : Cut("1/6", 0.1667)
    object Nothing : Cut("Nothing", 0.0)
    object Asaba : Cut("Asaba", -2.0)
    object SixthAndAsaba : Cut("1/6 + Asaba", -3.0)
}