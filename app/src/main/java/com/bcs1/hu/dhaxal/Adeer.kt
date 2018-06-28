package com.bcs1.hu.dhaxal

object Adeer : Relative("Adeer") {
    override fun cut(relatives: Relatives): Cut {
        if(hasMaleLeaves(relatives) || hasMaleRoots(relatives) || hasMaleSiblings(relatives)
                || exists(relatives, "Wiilka Walaal Rag", "Wiilka Walaal Rag Aabo")) {
            return Cut.Nothing
        }

        return Cut.Asaba
    }
}