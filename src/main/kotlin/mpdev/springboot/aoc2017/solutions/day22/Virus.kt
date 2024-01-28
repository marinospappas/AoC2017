package mpdev.springboot.aoc2017.solutions.day22

import mpdev.springboot.aoc2017.utils.Grid
import mpdev.springboot.aoc2017.utils.GridUtils
import mpdev.springboot.aoc2017.utils.Point

class Virus(input: List<String>) {

    val grid = Grid(input, Node.mapper, border = 0)
    val start = Point(grid.getDimensions().first / 2, grid.getDimensions().second / 2)
    val startDir = GridUtils.Direction.UP

    fun solve1(count: Int, part2: Boolean = false): Int {
        var infections = 0
        var curPosition = start
        var curDirection = startDir
        repeat(count) {
            when (grid.getDataPoint(curPosition)) {
                Node.INFECTED -> {
                    curDirection = curDirection.turnRight()
                    if (part2)
                        grid.setDataPoint(curPosition, Node.FLAGGED)
                    else
                        grid.removeDataPoint(curPosition)
                }
                Node.WEAKENED -> {
                    grid.setDataPoint(curPosition, Node.INFECTED)
                    infections++
                }
                Node.FLAGGED -> {
                    curDirection = curDirection.reverse()
                    grid.removeDataPoint(curPosition)
                }
                else -> {
                    curDirection = curDirection.turnLeft()
                    if (part2)
                        grid.setDataPoint(curPosition, Node.WEAKENED)
                    else {
                        grid.setDataPoint(curPosition, Node.INFECTED)
                        infections++
                    }
                }
            }
            curPosition += curDirection.increment
        }
        return infections
    }
}

enum class Node(val value: Char) {
    INFECTED('#'),
    WEAKENED('W'),
    FLAGGED('F');
    companion object {
        val mapper: Map<Char,Node> = values().associateBy { it.value }
    }
}