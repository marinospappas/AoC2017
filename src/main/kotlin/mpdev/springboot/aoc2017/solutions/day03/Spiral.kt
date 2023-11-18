package mpdev.springboot.aoc2017.solutions.day03

import mpdev.springboot.aoc2017.utils.min
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

class Spiral(input: List<String>) {

    var inputValue: Int

    init {
        inputValue = input[0].toInt()
    }

    fun findDistanceFromCentre(x: Int): Int {
        var spiralIndx = ceil(sqrt(x.toDouble())).toInt()
        if (spiralIndx % 2 == 0)
            ++spiralIndx
        val radius = (spiralIndx - 1) / 2
        val a1 = spiralIndx * spiralIndx - radius
        val a2 = a1 - 2 * radius
        val a3 = a2 - 2 * radius
        val a4 = a3 - 2 * radius
        return radius + min(abs(a1-x), abs(a2-x), abs(a3-x), abs(a4-x))
    }
}