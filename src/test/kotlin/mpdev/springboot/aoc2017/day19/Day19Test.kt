package mpdev.springboot.aoc2017.day19

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day19.Day19
import mpdev.springboot.aoc2017.solutions.day19.TubeNetwork
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day19Test {

    private val day = 19                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day19()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets up Network`() {
        val tubeNetwork = TubeNetwork(inputLines)
        tubeNetwork.grid.print()
        println("start: ${tubeNetwork.start}")
    }

    @Test
    @Order(3)
    fun `Walks the Grid`() {
        val tubeNetwork = TubeNetwork(inputLines)
        val result = tubeNetwork.walkGrid()
        println(result)
        assertThat(result.first).isEqualTo("ABCDEF")
        assertThat(result.second).isEqualTo(38)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("ABCDEF")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        puzzleSolver.solvePart1()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("38")
    }

}
