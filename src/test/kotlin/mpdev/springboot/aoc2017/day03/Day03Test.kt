package mpdev.springboot.aoc2017.day03

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day03.Day03
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day03Test {

    private val day = 3                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day03()                      ///////// Update this for a new dayN test
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

    @ParameterizedTest
    @CsvSource(
        "1,    0",
        "12,   3",
        "23,   2",
        "1024, 31"
    )
    @Order(3)
    fun `Solves Part 1`(input: String, expected: String) {
        puzzleSolver.inputData = listOf(input)
        assertThat(puzzleSolver.solvePart1().result).isEqualTo(expected)
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
