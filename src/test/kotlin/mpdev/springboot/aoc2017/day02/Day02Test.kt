package mpdev.springboot.aoc2017.day02

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day02.Day02
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day02Test {

    private val day = 2                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day02()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets Integer List`() {
        for (row in puzzleSolver.sSheet.indices)
            println(puzzleSolver.sSheet[row].toList())
        assertThat(puzzleSolver.sSheet.size).isEqualTo(3)
        assertThat(puzzleSolver.sSheet[0].size).isEqualTo(4)
        assertThat(puzzleSolver.sSheet[1].size).isEqualTo(3)
        assertThat(puzzleSolver.sSheet[2].size).isEqualTo(4)
    }

    @Test
    @Order(3)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("18")
    }

    @Test
    @Order(3)
    fun `Solves Part 2`() {
        puzzleSolver.inputData = listOf(
            "5 9 2 8",
            "9 4 7 3",
            "3 8 6 5"
        )
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("9")
    }
}
