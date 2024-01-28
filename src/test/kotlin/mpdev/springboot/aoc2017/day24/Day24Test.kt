package mpdev.springboot.aoc2017.day24

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day24.Day24
import mpdev.springboot.aoc2017.solutions.day24.ElectroMagnets
import mpdev.springboot.aoc2017.utils.println
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day24Test {

    private val day = 24                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day24()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets up Program`() {
        val electroMagnets = ElectroMagnets(inputLines)
        electroMagnets.components.forEach { println(it) }
        assertThat(electroMagnets.components.size).isEqualTo(8)
    }

    @Test
    @Order(3)
    fun `Finds Strongest Bridge`() {
        val electroMagnets = ElectroMagnets(inputLines)
        electroMagnets.findStrongestLongest()
        electroMagnets.longest.println()
        electroMagnets.strongest.println()
        electroMagnets.longestStrength.println()
        assertThat(electroMagnets.strongest).isEqualTo(31)
        assertThat(electroMagnets.longestStrength).isEqualTo(19)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("31")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        puzzleSolver.solvePart1()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("19")
    }

}
