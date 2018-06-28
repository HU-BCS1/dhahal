package com.bcs1.hu.dhaxal

object WalRagAabo: Relative("Walaal Rag Aabo") {
    fun hasWithHolder(relatives: Relatives) : Boolean {
        val (walaal_dumar) = getRelative(relatives, "Walaal Dumar")

        var withholderConditions = getRelative(relatives, "Wiil", "Wiilka Wiilka", "Aabo", "Walaal Rag")
                .map { it.count > 0 }

        if(walaal_dumar.count > 0) {
            withholderConditions += walaal_dumar.cut(relatives) == Cut.Asaba
        }

        return withholderConditions.any { it }
    }

    override fun cut(relatives: Relatives): Cut {
        return if(hasWithHolder(relatives)) Cut.Nothing else Cut.Asaba
    }
}