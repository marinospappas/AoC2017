package mpdev.springboot.aoc2017.day21

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day21.Day21
import mpdev.springboot.aoc2017.solutions.day21.PatternEnhancer
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
    fun `Reads Input and sets up Simulator`() {
        val patternEnhancer = PatternEnhancer(inputLines)
        patternEnhancer.rules.forEach {
            println("from:")
            it.first.print()
            println("to:")
            it.second.print()
        }
        assertThat(patternEnhancer.rules.size).isEqualTo(2)
    }

    @Test
    @Order(3)
    fun `Simulates Particle Movement`() {
        val patternEnhancer = PatternEnhancer(inputLines)

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
