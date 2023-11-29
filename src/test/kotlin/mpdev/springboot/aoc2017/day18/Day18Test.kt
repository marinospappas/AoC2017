package mpdev.springboot.aoc2017.day18

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day18.Day18
import mpdev.springboot.aoc2017.solutions.day18.Duet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day18Test {

    private val day = 18                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day18()                      ///////// Update this for a new dayN test
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
        val duet = Duet(inputLines)
        duet.program1.instructionList.forEach { println(it) }
        assertThat(duet.program1.instructionList.size).isEqualTo(10)
    }

    @Test
    @Order(3)
    fun `Executes Program`() {
        val duet = Duet(inputLines)
        val result = duet.run1()
        println(result)
        assertThat(result).isEqualTo(4)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("4")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        puzzleSolver.inputData = listOf(
            "snd 1",
            "snd 2",
            "snd p",
            "rcv a",
            "rcv b",
            "rcv c",
            "rcv d"
        )
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("3")
    }

}
