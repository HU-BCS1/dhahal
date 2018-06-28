package com.bcs1.hu.dhaxal

object Xaas: Relative("Xaas", max = 4, gender = Gender.Female) {
    override fun cut(relatives: Relatives): Cut {
        return if(hasLeaves(relatives)) Cut.Eighth else Cut.Quarter
    }
}