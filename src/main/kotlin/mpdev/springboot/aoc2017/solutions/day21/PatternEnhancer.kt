package mpdev.springboot.aoc2017.solutions.day21

import mpdev.springboot.aoc2017.utils.Grid

class PatternEnhancer(input: List<String>) {

    val rules = mutableListOf<Pair<Grid<Pixel>, Grid<Pixel>>>()
    val initialPattern = listOf(
        ".#.",
        "..#",
        "###"
    )

    init {
        processInput(input)
    }

    fun enhance(): Int {
        val initialGrid = Grid(initialPattern, mapper = Pixel.mapper, border = 0)

        return 0
    }

    private fun processInput(input: List<String>) {
        input.forEach { line ->
            val (s1, s2) = line.split(" => ")
            val grid1 = Grid(s1.split('/'), mapper = Pixel.mapper, border = 0)
            val grid2 = Grid(s2.split('/'), mapper = Pixel.mapper, border = 0)
            rules.add(Pair(grid1, grid2))
        }
    }
}

enum class Pixel(val value: Char) {
    ON('#'),
    OFF('.');
    companion object {
        val mapper: Map<Char,Pixel> = values().associateBy { it.value }
    }
}