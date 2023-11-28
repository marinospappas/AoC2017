package mpdev.springboot.aoc2017.day16

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day16.Day16
import mpdev.springboot.aoc2017.solutions.day16.Transformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day16Test {

    private val day = 16                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day16()                      ///////// Update this for a new dayN test
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
        val transformation = Transformation(inputLines)
        transformation.transList.forEach { println(it) }
        assertThat(transformation.transList.size).isEqualTo(3)
    }

    @Test
    @Order(3)
    fun `Executes Transformations`() {
        val transformation = Transformation(inputLines)
        transformation.programs = ('a'..'e').toList()
        val result = transformation.runOnce()
        println(result)
        assertThat(result).isEqualTo("baedc")
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        puzzleSolver.transformation.programs = ('a'..'e').toList()
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("baedc")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        puzzleSolver.transformation.programs = ('a'..'e').toList()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("abcde")
    }

}
