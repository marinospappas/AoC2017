package mpdev.springboot.aoc2017.day11

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day11.Day11
import mpdev.springboot.aoc2017.solutions.day11.HexPath
import mpdev.springboot.aoc2017.utils.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day11Test {

    private val day = 11                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day11()                      ///////// Update this for a new dayN test
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
    fun `Reads Input`() {
        val grid = HexPath(inputLines)
        println(grid.steps)
        assertThat(grid.steps.size).isEqualTo(5)
    }

    @Test
    @Order(3)
    fun `Finds Target Point`() {
        val grid = HexPath(inputLines)
        val target = grid.getTargetPoint()
        println(target)
        println(grid.distances)
        assertThat(target).isEqualTo(Point(-1,-5))
        assertThat(grid.distances.last()).isEqualTo(3)
        assertThat(grid.distances.max()).isEqualTo(3)
    }

    @Test
    @Order(4)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("3")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("3")
    }
}
