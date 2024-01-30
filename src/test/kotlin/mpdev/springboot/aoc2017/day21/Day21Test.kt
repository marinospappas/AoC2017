package mpdev.springboot.aoc2017.day21

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day21.Day21
import mpdev.springboot.aoc2017.solutions.day21.PatternEnhancer
import mpdev.springboot.aoc2017.solutions.day21.Pixel
import mpdev.springboot.aoc2017.utils.Grid
import mpdev.springboot.aoc2017.utils.println
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day21Test {

    private val day = 21                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day21()                      ///////// Update this for a new dayN test
    private val inputDataReader = InputDataReader("src/test/resources/inputdata/input")
    private var inputLines: List<String> = inputDataReader.read(day)

    @BeforeEach
    fun setup() {
        puzzleSolver.setDay()
        puzzleSolver.inputData = inputLines
        puzzleSolver.initSolver()
    }

    @Test
    @Order(1)
    fun `Sets Day correctly`() {
        assertThat(puzzleSolver.day).isEqualTo(day)
    }

    @Test
    @Order(2)
    fun `Reads Input and sets up Rules Base`() {
        val patternEnhancer = PatternEnhancer(inputLines)
        patternEnhancer.rules.forEach {
            println("from:")
            it.first.print()
            println("to:")
            it.second.print()
        }
        patternEnhancer.rules2.forEach {
            println("from:")
            it.first.println()
            println("to:")
            it.second.println()
        }
        patternEnhancer.rules3.forEach {
            println("from:")
            it.first.println()
            println("to:")
            it.second.println()
        }
        println("initial grid")
        patternEnhancer.initialGrid.print()
        patternEnhancer.initialList.println()
        assertThat(patternEnhancer.rules.size).isEqualTo(2)
    }

    @Test
    @Order(3)
    fun `Simulates Pattern Enhancement`() {
        val patternEnhancer = PatternEnhancer(inputLines)
        val enhancedGrid = patternEnhancer.enhance(2).also { it.println() }
        val result = enhancedGrid.sumOf { it.countOneBits() }.also { it.println() }
        assertThat(result).isEqualTo(12)
    }

    @Test
    fun test1() {
        var g = Grid(listOf(".#.", "..#", "###"), Pixel.mapper, border = 0)
        g.print()
        g = g.flipV()
        g.print()
        g = g.flipV().flipH()
        g.print()
        g = g.flipV()
        g.print()
        g = g.rotate()
        g.print()
        g = g.flipV()
        g.print()
        g = g.flipV().flipH()
        g.print()
        g = g.flipV()
        g.print()
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("0")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("1")
    }

}
