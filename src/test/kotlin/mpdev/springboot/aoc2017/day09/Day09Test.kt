package mpdev.springboot.aoc2017.day09

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day09.Day09
import mpdev.springboot.aoc2017.solutions.day09.StreamOfChars
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day09Test {

    private val day = 9                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day09()                      ///////// Update this for a new dayN test
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
        val stream = StreamOfChars(inputLines)
        val (sum1,sum2) = stream.processStream()
        println("$sum1, $sum2")
        assertThat(sum1).isEqualTo(3)
        assertThat(sum2).isEqualTo(17)
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource(
        "{}, 1",
        "{{{}}}, 6",
        "'{{},{}}', 5",
        "'{{{},{},{{}}}}', 16",
        "'{<a>,<a>,<a>,<a>}', 1",
        "'{{<ab>},{<ab>},{<ab>},{<ab>}}', 9",
        "'{{<!!>},{<!!>},{<!!>},{<!!>}}', 9",
        "'{{<a!>},{<a!>},{<a!>},{<ab>}}', 3"
    )
    fun `Processes Stream 1`(input: String, expected: Int) {
        val stream = StreamOfChars(listOf(input))
        assertThat(stream.processStream().first).isEqualTo(expected)
    }

    @Test
    @Order(4)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("3")
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource(
        "<>, 0",
        "<random characters>, 17",
        "<<<<>, 3",
        "<{!>}>, 2",
        "<!!>, 0",
        "<!!!>>, 0",
        "'<{o\"i!a,<{i<a>', 10"
    )
    fun `Processes Stream 2`(input: String, expected: Int) {
        val stream = StreamOfChars(listOf(input))
        assertThat(stream.processStream().second).isEqualTo(expected)
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("17")
    }
}
