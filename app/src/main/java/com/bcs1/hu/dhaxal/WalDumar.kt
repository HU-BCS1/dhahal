package com.bcs1.hu.dhaxal

object WalDumar: Relative("Walaal Dumar", gender = Gender.Female) {

    fun hasWithHolder(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Wiil", "Wiilka Wiilka", "Aabo")
                .any { it.count > 0 }
    }

    override fun cut(relatives: Relatives): Cut {
        val (walaal_rag, gabadh) = getRelative(relatives, "Walaal Rag", "Gabadh")

        if(hasWithHolder(relatives)) return Cut.Nothing

        if(walaal_rag.count > 0 || gabadh.count > 0) {
            return Cut.Asaba
        }

        return if(this.count == 1) Cut.Half else  return Cut.TwoThird
    }
}