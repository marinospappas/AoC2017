package mpdev.springboot.aoc2017.day04

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day04.Day04
import mpdev.springboot.aoc2017.utils.isAnagram
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day04Test {

    private val day = 4                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day04()                      ///////// Update this for a new dayN test
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
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("2")
    }

    @ParameterizedTest
    @CsvSource(
        "abcd, acdb, true",
        "axyz, ijkl, false",
        "iioi, oiii, true"
    )
    @Order(3)
    fun `Is Anagram`(s1: String, s2: String, expected: Boolean) {
        assertThat(s1.isAnagram(s2)).isEqualTo(expected)
    }

    @Test
    @Order(4)
    fun `Solves Part 2`() {
        puzzleSolver.inputData = listOf(
            "abcde fghij",
            "abcde xyz ecdab",
            "a ab abc abd abf abj",
            "iiii oiii ooii oooi oooo",
            "oiii ioii iioi iiio"
        )
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("3")
    }
}
