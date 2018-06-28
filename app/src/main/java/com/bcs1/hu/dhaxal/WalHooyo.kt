package com.bcs1.hu.dhaxal

object WalHooyo: Relative("Walaal Hooyo") {
    override fun cut(relatives: Relatives): Cut {
        if(hasLeaves(relatives) || hasMaleRoots(relatives)) {
            return Cut.Nothing
        }

        if(this.count == 1)  {
            return Cut.Sixth
        } else {
            return Cut.Third
        }
    }
}