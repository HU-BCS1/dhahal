package com.bcs1.hu.dhaxal

object WAdeerAabo : Relative("Wiil Adeer Aabo") {
    override fun cut(relatives: Relatives): Cut {
        if(hasMaleLeaves(relatives) || hasMaleRoots(relatives) || hasMaleSiblings(relatives)
                || exists(relatives, "Wiilka Walaal Rag", "Wiilka Walaal Rag Aabo", "Adeer", "Adeer Aabo", "Wiil Adeer")) {
            return Cut.Nothing
        }

        return Cut.Asaba
    }
}