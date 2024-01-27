package mpdev.springboot.aoc2017.day25

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day25.Day25
import mpdev.springboot.aoc2017.solutions.day25.Diagnostic
import mpdev.springboot.aoc2017.utils.println
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day25Test {

    private val day = 25                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day25()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets up Diagnostics`() {
        val diagnostic = Diagnostic(inputLines)
        diagnostic.startState.println()
        diagnostic.countSteps.println()
        diagnostic.states.forEach { it.println() }
        assertThat(diagnostic.states.size).isEqualTo(2)
        assertThat(diagnostic.startState).isEqualTo("A")
    }

    @Test
    @Order(3)
    fun `Executes Diagnostics Steps`() {
        val diagnostic = Diagnostic(inputLines)
        val result = diagnostic.execute().also { it.println() }
        diagnostic.tape.toSortedMap().println()
        assertThat(result).isEqualTo(3)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("3")
    }

}
