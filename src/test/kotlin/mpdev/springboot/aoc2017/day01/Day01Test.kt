package mpdev.springboot.aoc2017.day01

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day01.Day01
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day01Test {

    private val day = 1                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day01()                      ///////// Update this for a new dayN test
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
    fun `Reads Input ans sets Integer List`() {
        println(puzzleSolver.intList.toList())
        assertThat(puzzleSolver.intList.toList()).isEqualTo(listOf(1,1,2,2))
    }

    @ParameterizedTest
    @CsvSource(
        "1122,     3",
        "1111,     4",
        "1234,     0",
        "91212129, 9"
    )
    @Order(3)
    fun `Solves Part 1`(input: String, expected: String) {
        puzzleSolver.inputData = listOf(input)
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart1().result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "1212,     6",
        "1221,     0",
        "123425,   4",
        "123123,   12",
        "12131415, 4"
    )    @Order(3)
    fun `Solves Part 2`(input: String, expected: String) {
        puzzleSolver.inputData = listOf(input)
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo(expected)
    }
}
