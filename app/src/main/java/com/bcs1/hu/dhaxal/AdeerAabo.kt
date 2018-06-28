package com.bcs1.hu.dhaxal

object AdeerAabo : Relative("Adeer Aabo") {
    override fun cut(relatives: Relatives): Cut {
        if(hasMaleLeaves(relatives) || hasMaleRoots(relatives) || hasMaleSiblings(relatives)
                || exists(relatives, "Wiilka Walaal Rag", "Wiilka Walaal Rag Aabo", "Adeer")) {
            return Cut.Nothing
        }

        return Cut.Asaba
    }
}