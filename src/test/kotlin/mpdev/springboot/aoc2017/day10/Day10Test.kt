package mpdev.springboot.aoc2017.day10

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day10.Day10
import mpdev.springboot.aoc2017.solutions.day10.KnotHash
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day10Test {

    private val day = 10                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day10()                      ///////// Update this for a new dayN test
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
        val hash = KnotHash(inputLines)
        println(hash.data)
        println(hash.lengthsP1)
        println(hash.lengthsP2)
        assertThat(hash.data.size).isEqualTo(5)
        assertThat(hash.lengthsP1.size).isEqualTo(4)
    }

    @Test
    @Order(2)
    fun `Generates Knot Hash 1 Run`() {
        val h = KnotHash(inputLines)
        val hash = h.hash1()
        println(hash)
        assertThat(hash[0]*hash[1]).isEqualTo(12)
    }

    @Test
    @Order(4)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("12")
    }

    @Test
    @Order(5)
    fun `Calculates Dense Hash`() {
        val h = KnotHash(inputLines)
        val result = h.denseHash(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22), 1)
        println(result)
    }

    @ParameterizedTest
    @Order(7)
    @CsvSource(
        "'AoC 2017', 33efeb34ea91902bb2f59c9920caa6cd",
        "'',         a2582a3a0e66e6e86e3812dcb672a272",
        "'1,2,3',    3efbe78a8d82f29979031a4aa0b16a9d",
        "'1,2,4',    63960835bcdc130f0b66d7ff4f6a5a8e"
    )
    fun `Solves Part 2`(input: String, expected: String) {
        puzzleSolver.inputData = listOf(input)
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo(expected)
    }
}
