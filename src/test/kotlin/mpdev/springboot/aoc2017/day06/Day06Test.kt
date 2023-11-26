package mpdev.springboot.aoc2017.day06

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day06.Day06
import mpdev.springboot.aoc2017.solutions.day06.Memory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day06Test {

    private val day = 6                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day06()                      ///////// Update this for a new dayN test
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
        val mem = Memory(inputLines)
        println(mem.memBanks)
        assertThat(mem.memBanks.size).isEqualTo(4)
    }

    @Test
    @Order(3)
    fun `Redistributes blocks`() {
        val mem = Memory(inputLines)
        val (cycles, loopSize) = mem.rearrangeMemoryAndDetectLoop()
        println("cycles: $cycles. loop size: $loopSize")
        assertThat(cycles).isEqualTo(5)
        assertThat(loopSize).isEqualTo(4)
    }

    @Test
    @Order(4)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("5")
    }

    @Test
    @Order(5)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("4")
    }
}
