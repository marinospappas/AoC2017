package mpdev.springboot.aoc2017.solutions.day21

import mpdev.springboot.aoc2017.utils.Grid
import mpdev.springboot.aoc2017.utils.println
import kotlin.math.sqrt

class PatternEnhancer(input: List<String>) {

    val rules = mutableListOf<Pair<Grid<Pixel>, Grid<Pixel>>>()
    val rules2: List<Pair<List<Int>, List<Int>>>
    val rules3: List<Pair<List<Int>, List<Int>>>
    val initialGrid = Grid(listOf(".#.", "..#", "###"), Pixel.mapper, border = 0)
    val initialList = (0 .. 2).map { initialGrid.mapRowToInt(it) }

    init {
        processInput(input)
        rules2 = getRules(2)
        rules3 = getRules(3)
    }

    fun enhance(count: Int): List<Int> {
        var gridSize = initialList.size
        var grid = initialList
        repeat(count) {
            grid = enhanceGrid(grid, if (gridSize % 2 == 0) 2 else 3)
            gridSize = grid.size
            println("grid size $gridSize")
            grid.forEach { it.toString(2).padEnd(gridSize, '0').println() }
            println("count: ${grid.sumOf { it.countOneBits() }}")
        }
        return grid
    }

    fun enhanceGrid(grid: List<Int>, factor: Int): List<Int> {
        val subGrids = decomposeGrid(grid, factor)
        val enhancedSubGrids = subGrids.map { match(it, factor) }
        return composeGrid(enhancedSubGrids, factor+1)
    }

    fun decomposeGrid(grid: List<Int>, factor: Int): List< List<Int> > {
        val subGrids = mutableListOf<List<Int>>()
        val loopCount = grid.size / factor
        var mask = if (factor == 2) 3 else 7
        for (i in 0 until loopCount) {
            for (j in 0 until loopCount) {
                val sublist = grid.subList(j * factor, j * factor + factor)
                subGrids.add(sublist.map { it.and(mask).shr(i * factor) })
            }
            mask *= if (factor == 2) 4 else 8
        }
        return subGrids
    }

    fun match(grid: List<Int>, factor: Int): List<Int> {
        val rules = if (factor == 2) rules2 else rules3
        rules.forEach {
            if (it.first == grid) {
                println("matched rule ${it.first}")
                println("matched grid")
                it.first.forEach { x -> x.toString(2).padStart(it.first.size, '0').println() }
                println("to be replaced by grid ${it.second}")
                it.second.forEach { x -> x.toString(2).padStart(it.second.size, '0').println() }
            }
        }
        return rules.first { it.first == grid }.second
    }

    fun composeGrid(subgrids: List< List<Int> >, factor: Int): List<Int> {
        val grid = mutableListOf<Int>()
        val loopCount = sqrt(subgrids.size.toDouble()).toInt()
        for (i in 0 until loopCount) {
            for (j in 0 until loopCount) {
                if (i == 0)
                    grid.addAll(subgrids[j * loopCount])
                else
                    for (k in j * factor until j * factor + factor)
                        grid[k] = grid[k].or((subgrids[j * loopCount][k - j*factor]).shl(i * factor))
            }
        }
        return grid
    }

    private fun getRules(ruleIndex: Int): List<Pair<List<Int>, List<Int>>> {
        val adjustedRules = mutableListOf<Pair<List<Int>, List<Int>>>()
        rules.filter { it.first.getDimensions().first == ruleIndex }.forEach { rule ->
            val substitute = (0 .. ruleIndex).map { rule.second.mapRowToInt(it) }
            var match = (0 until ruleIndex).map { rule.first.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            var grid = rule.first.flipV()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.flipV().flipH()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.flipV()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.rotate()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.flipV()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.flipV().flipH()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
            grid = grid.flipV()
            match = (0 until ruleIndex).map { grid.mapRowToInt(it) }
            adjustedRules.add(Pair(match, substitute))
        }
        return adjustedRules
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