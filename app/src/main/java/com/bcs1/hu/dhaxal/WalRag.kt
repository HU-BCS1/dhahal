package com.bcs1.hu.dhaxal

object WalRag: Relative("Walaal Rag") {
    fun hasWithHolder(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Wiil", "Wiilka Wiilka", "Aabo")
                .any { it.count > 0 }
    }

    override fun cut(relatives: Relatives): Cut {
        return if(hasWithHolder(relatives)) Cut.Nothing else Cut.Asaba
    }
}