package mpdev.springboot.aoc2017.solutions.day14

import mpdev.springboot.aoc2017.solutions.day10.KnotHash
import mpdev.springboot.aoc2017.utils.Grid
import mpdev.springboot.aoc2017.utils.Point

class DiskDefragmenter(input: List<String>) {

    val hashes = (0..127).map { KnotHash.compute(input[0] + "-" + it.toString()) }
    private val diskInfo = Array(128) { row ->
        hashes[row].toCharArray()
                .joinToString("") { c -> Integer.toBinaryString(Integer.parseInt(c.toString(), 16)).padStart(4, '0') }
                .replace("1","#")
    }.toList()
    val grid = Grid(diskInfo, Sector.mapper, 0)

    fun countUsed() = grid.countOf(Sector.FULL)

    fun countGroups(): Int {
        val groups = mutableSetOf<Set<Point>>()
        grid.getDataPoints().entries.filter { it.value == Sector.FULL }.mapTo(groups) { grid.getAdjacentArea(it.key) }
        return groups.size
    }
}

enum class Sector(val value: Char) {
    FULL('#');
    companion object {
        val mapper: Map<Char,Sector> = values().associateBy { it.value }
    }
}