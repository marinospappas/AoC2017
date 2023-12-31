package mpdev.springboot.aoc2017.day05

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day05.Day05
import mpdev.springboot.aoc2017.solutions.day05.Instructions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day05Test {

    private val day = 5                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day05()                      ///////// Update this for a new dayN test
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
    fun `Executes Jump Sequence`() {
        val instr = Instructions(inputLines)
        println(instr.jumpOffsets)
        assertThat(instr.jumpOffsets.size).isEqualTo(5)
        val result = instr.executeJumpSeries1()
        println(result)
        assertThat(result).isEqualTo(5)
    }

    @Test
    @Order(3)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("5")
    }

    @Test
    @Order(4)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("10")
    }
}
