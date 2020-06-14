package com.flaringapp.sortvisualiztion.utils

object DataUtils {

    fun map(x: Float, a1: Float, a2: Float, b1: Float, b2: Float): Float {
        return b1 + ((x - a1) * (b2 - b1) / (a2 - a1))
    }

    fun map(x: Int, a1: Int, a2: Int, b1: Float, b2: Float): Float {
        return b1 + ((x - a1).toFloat() * (b2 - b1) / (a2 - a1).toFloat())
    }
}

fun IntArray.swap(from: Int, to: Int) {
    val temp = this[from]
    this[from] = this[to]
    this[to] = temp
}

fun Collection<Int>.format(): String {
    return this.joinToString(
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )
}

fun Array<Number>.format(): String {
    return this.joinToString(
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )
}

fun IntArray.format(): String {
    return this.joinToString(
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )
}