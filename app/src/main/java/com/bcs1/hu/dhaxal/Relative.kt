package com.bcs1.hu.dhaxal

abstract class Relative(val name: String, val max: Int = 99, val gender: Gender = Gender.Male) {
    var count: Int = 0

    abstract fun cut(relatives: Relatives) : Cut

    open fun add(relatives: Relatives) {
        if(++count > max) count = max
    }

    fun remove() {
        if(--count < 0) count = 0
    }

    fun getRelative(relatives: Relatives, vararg types: String) = types.map (relatives::getValue)

    fun exists(relatives: Relatives, vararg types: String) = getRelative(relatives, *types).any { it.count > 0 }

    fun hasLeaves(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Wiil", "Gabadh", "Wiilka Wiilka", "Gabadha Wiilka")
                .any { it.count > 0 }
    }

    fun hasMaleLeaves(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Wiil", "Wiilka Wiilka")
                .any { it.count > 0 }
    }

    fun hasMaleRoots(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Aabo", "Awoowe")
                .any { it.count > 0 }
    }

    fun hasMaleSiblings(relatives: Relatives) : Boolean {
        return getRelative(relatives, "Walaal Rag", "Walaal Rag Aabo")
                .any { it.count > 0 }
    }
}

