package com.bcs1.hu.dhaxal

object Nin: Relative("Nin", max = 1) {

    override fun cut(relatives: Relatives): Cut {
        return if(hasLeaves(relatives)) Cut.Quarter else Cut.Half
    }
}