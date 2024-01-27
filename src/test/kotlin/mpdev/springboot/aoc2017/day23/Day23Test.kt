package mpdev.springboot.aoc2017.day23

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day23.CoProcessor
import mpdev.springboot.aoc2017.solutions.day23.Day23
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day23Test {

    private val day = 23                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day23()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets up Program`() {
        val coProcessor = CoProcessor(inputLines)
        coProcessor.program1.instructionList.forEach { println(it) }
        assertThat(coProcessor.program1.instructionList.size).isEqualTo(32)
    }

    @Test
    @Order(5)
    fun `Debug Part 1`() {
        val coProcessor = CoProcessor(inputLines)
        coProcessor.program1.debug = true
        runBlocking {
            val job = launch { coProcessor.program1.run(true) }
            coProcessor.program1.waitProgram(job)
        }
    }

    @Test
    @Order(7)
    fun `Debug Part 2`() {
        val coProcessor = CoProcessor(inputLines)
        println(coProcessor.part2())
    }

}
