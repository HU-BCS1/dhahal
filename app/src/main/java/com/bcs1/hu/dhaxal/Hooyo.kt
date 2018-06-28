package com.bcs1.hu.dhaxal

object Hooyo: Relative("Hooyo", max = 1, gender = Gender.Female) {

    fun hasGroupOfSiblings(relatives: Relatives) : Boolean {
        return getRelative(relatives,"Walaal Rag", "Walaal Dumar", "Walaal Rag Aabo", "Walaal Dumar Aabo", "Walaal Hooyo")
                .map(Relative::count)
                .sum() > 1
    }

    override fun cut(relatives: Relatives): Cut {
        val (aabo, xaas, nin) = getRelative(relatives, "Aabo", "Xaas", "Nin")

        if(hasLeaves(relatives) || hasGroupOfSiblings(relatives)) {
            return Cut.Sixth
        }

        if(aabo.count == 1) {
            if(xaas.count > 0) {
                return Cut.Quarter
            }
            if(nin.count == 1) {
                return Cut.Sixth
            }
        }


        return Cut.Third
    }
}