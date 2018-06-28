package com.bcs1.hu.dhaxal

object WWalRag : Relative("Wiilka Walaal Rag") {
    override fun cut(relatives: Relatives): Cut {
        if(hasMaleLeaves(relatives) || hasMaleRoots(relatives) || hasMaleSiblings(relatives)) {
            return Cut.Nothing
        }

        return Cut.Asaba
    }

}