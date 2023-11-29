package mpdev.springboot.aoc2017.solutions.day19

import mpdev.springboot.aoc2017.solutions.day19.Direction.*
import mpdev.springboot.aoc2017.solutions.day19.Segment.*
import mpdev.springboot.aoc2017.utils.Grid
import mpdev.springboot.aoc2017.utils.Point

class TubeNetwork(input: List<String>) {

    val grid = Grid(input, Segment.mapper, border = 0, defaultChar = ' ')
    val start = grid.getDataPoints().entries.filter { it.key.y == 0 }.first { it.value == VERT }.key

    fun walkGrid(): Pair<String,Int> {
        var direction = DOWN
        var current = start
        var result = ""
        var steps = 0
        while (true) {
            ++steps
            current = when (direction) {
                DOWN -> {
                    current += Point(0,1)
                    if (grid.getDataPoint(current) == CROSS) {
                        direction = if (grid.getDataPoint(current+Point(1,0)) == null) LEFT
                        else RIGHT
                    }
                    current
                }
                LEFT -> {
                    current += Point(-1,0)
                    if (grid.getDataPoint(current) == CROSS) {
                        direction = if (grid.getDataPoint(current+Point(0,1)) == null) UP
                        else DOWN
                    }
                    current
                }
                RIGHT ->  {
                    current += Point(1,0)
                    if (grid.getDataPoint(current) == CROSS) {
                        direction = if (grid.getDataPoint(current+Point(0,1)) == null) UP
                        else DOWN
                    }
                    current
                }
                UP -> {
                    current += Point(0,-1)
                    if (grid.getDataPoint(current) == CROSS) {
                        direction = if (grid.getDataPoint(current+Point(1,0)) == null) LEFT
                        else RIGHT
                    }
                    current
                }
            }
            if ((grid.getDataPoint(current) ?: break) in A..Z)
                result += grid.getDataPoint(current)!!.value
        }
        return Pair(result, steps)
    }

}

enum class Segment(val value: Char) {
    VERT('|'),
    HORIZ('-'),
    CROSS('+'),
    A('A'), B('B'), C('C'), D('D'), E('E'), F('F'), G('G'), H('H'), I('I'),
    J('J'), K('K'), L('L'), M('M'), N('N'), O('O'), P('P'), Q('Q'), R('R'),
    S('S'), T('T'), U('U'), V('V'), W('W'), X('X'), Y('Y'), Z('Z');
    companion object {
        val mapper: Map<Char,Segment> = values().associateBy { it.value }
    }
}

enum class Direction { DOWN, LEFT, RIGHT, UP }