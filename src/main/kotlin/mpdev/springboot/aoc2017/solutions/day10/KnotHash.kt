package mpdev.springboot.aoc2017.solutions.day10

import mpdev.springboot.aoc2017.utils.CircularList

class KnotHash(input: List<String>) {

    val data = if (input.size > 1)
        input[1].split(Regex(", ?")).map { Integer.parseInt(it) }    // test data
    else (0..255).map { it }
    val lengthsP1 = try {
        input[0].split(Regex(""", ?""")).map { Integer.parseInt(it) }
    }
    catch (e: Exception) { listOf() }  // for testing
    val lengthsP2 = input[0].toCharArray().map { it.code } + listOf(17, 31, 73, 47, 23)
    private var skipLength = 0

    fun hash1(list: CircularList<Int> = CircularList(data.toMutableList()),
              lengths: List<Int> = lengthsP1): List<Int> {
        lengths.forEach { length ->
            val sublist = list.sublist(length)
            list.setSubList(sublist.reversed())
            list.incrCurPos(length + skipLength++)
        }
        return list.toList()
    }

    fun sparseHash(): List<Int> {
        val list = CircularList(data.toMutableList())
        skipLength = 0
        (1..64).forEach { _ -> hash1(list, lengthsP2) }
        return list.toList()
    }

    fun denseHash(list: List<Int>, length: Int = 16): String {
        val result = mutableListOf<Int>()
        for (i in 0 until length) {
            val s = list.subList(i*16, (i+1)*16)
            result.add(s.reduce { acc, it -> acc.xor(it) })
        }
        return result.joinToString("") { "%02x".format(it) }
    }
}