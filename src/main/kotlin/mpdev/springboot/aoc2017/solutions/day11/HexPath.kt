package mpdev.springboot.aoc2017.solutions.day11

import mpdev.springboot.aoc2017.utils.Point
import kotlin.math.abs

class HexPath(input: List<String>) {

    val steps = input[0].split(',').map { Directions.from(it) }
    val distances = mutableListOf<Int>()

    fun getTargetPoint(): Point {
        var current = Point(0,0)
        distances.clear()
        steps.forEach {
            current += it.step
            distances.add((abs(current.x) + abs(current.y)) / 2)
        }
        return current
    }

    enum class Directions(val strValue: String, val step: Point) {
        N("n", Point(0, 2)),
        NE("ne", Point(1, 1)),
        SE("se", Point(1, -1)),
        S("s", Point(0, -2)),
        SW("sw", Point(-1, -1)),
        NW("nw", Point(-1, 1));
        companion object {
            fun from(s: String) = values().first { it.strValue == s }
        }
    }
}