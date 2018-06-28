package com.bcs1.hu.dhaxal

object WalDumarAabo: Relative("Walaal Dumar Aabo", gender = Gender.Female) {

    fun hasWithHolder(relatives: Relatives) : Boolean {
        val (walaal_dumar, walaal_rag_aabo) = getRelative(relatives, "Walaal Dumar", "Walaal Rag Aabo")
        var withholderConditions = getRelative(relatives, "Wiil", "Wiilka Wiilka", "Aabo", "Walaal Rag")
                .map { it.count > 0 }

        if(walaal_dumar.count > 0) {
            withholderConditions += if(walaal_dumar.count == 1)
                walaal_dumar.cut(relatives) == Cut.Asaba
            else
                walaal_rag_aabo.count > 0
        }

        return withholderConditions.any { it }
    }

    override fun cut(relatives: Relatives): Cut {

        val (gabadh, walaal_rag, walaal_dumar, walaal_rag_aabo) =
                getRelative(relatives, "Gabadh", "Walaal Rag", "Walaal Dumar", "Walaal Rag Aabo")

        if(hasWithHolder(relatives)) return Cut.Nothing

        if(walaal_rag_aabo.count == 0) {
            if(walaal_rag.count == 0 && walaal_dumar.count == 0) {
                if(this.count == 1) {
                    return Cut.Half
                } else {
                    return Cut.TwoThird
                }
            }
            if(walaal_dumar.cut(relatives) == Cut.Half && walaal_rag.count == 0) {
                return Cut.Sixth
            }
        }

        if(walaal_rag_aabo.count > 0 || gabadh.count > 0) {
            return Cut.Asaba
        }

        return Cut.Nothing
    }
}