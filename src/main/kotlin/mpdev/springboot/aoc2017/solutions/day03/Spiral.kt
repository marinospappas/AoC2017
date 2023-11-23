package mpdev.springboot.aoc2017.solutions.day03

import mpdev.springboot.aoc2017.utils.Point
import mpdev.springboot.aoc2017.utils.min
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

class Spiral(input: List<String>) {

    var inputValue: Int
    var data: MutableMap<Point,Int> = mutableMapOf()

    init {
        inputValue = input[0].toInt()
    }

    fun findDistanceFromCentre(x: Int): Int {
        var spiralIndx = ceil(sqrt(x.toDouble())).toInt()
        if (spiralIndx % 2 == 0)
            ++spiralIndx
        val radius = (spiralIndx - 1) / 2
        val a4 = spiralIndx * spiralIndx - radius
        val a3 = a4 - 2 * radius
        val a2 = a3 - 2 * radius
        val a1 = a2 - 2 * radius
        return radius + min(abs(a1-x), abs(a2-x), abs(a3-x), abs(a4-x))
    }

    fun fillSpiral(): Int {
        var radius = 0
        data[Point(0,0)] = 1
        var stopInt = 0
        while (stopInt == 0) {
            stopInt = fillNextRound(++radius)
        }
        return stopInt
    }

    private fun fillNextRound(r: Int): Int {
        val side = 2*r + 1
        val start = Point(r, -r+1)
        val points = mutableListOf(start)
        for (i in 0 until side-2)
            points.add(points.last() + Point(0,1))
        for (i in 0 until side-1)
            points.add(points.last() + Point(-1,0))
        for (i in 0 until side-1)
            points.add(points.last() + Point(0,-1))
        for (i in 0 until side-1)
            points.add(points.last() + Point(1,0))
        points.forEach { p -> run {
            val sum =  p.adjacent().map { neighbour -> data[neighbour] ?: 0 }.sum().also { data[p] = it }
            if (sum > inputValue)
                return sum
        }}
        return 0
    }

    fun printData() {
        val minX = data.keys.minOf { it.x }
        val minY = data.keys.minOf { it.y }
        val maxX = data.keys.maxOf { it.x }
        val maxY = data.keys.maxOf { it.y }
        print("      ")
        for (col in minX..maxX)
            print("%6d ".format(col))
        println()
        for (row in maxY downTo minY) {
            print("%6d ".format(row))
            for (col in minX..maxX)
                print("%6d ".format(data[Point(col,row)]))
            println()
        }
    }
}