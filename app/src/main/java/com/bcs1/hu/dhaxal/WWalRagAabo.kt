package com.bcs1.hu.dhaxal

object WWalRagAabo : Relative("Wiilka Walaal Rag Aabo") {
    override fun cut(relatives: Relatives): Cut {
        if(hasMaleLeaves(relatives) || hasMaleRoots(relatives) || hasMaleSiblings(relatives) || exists(relatives, "Wiilka Walaal Rag")) {
            return Cut.Nothing
        }

        return Cut.Asaba
    }

}