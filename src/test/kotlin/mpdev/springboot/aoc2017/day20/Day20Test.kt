package mpdev.springboot.aoc2017.day20

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day20.Day20
import mpdev.springboot.aoc2017.solutions.day20.ParticleSimulator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day20Test {

    private val day = 20                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day20()                      ///////// Update this for a new dayN test
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
    fun `Reads Input and sets up Simulator`() {
        val simulator = ParticleSimulator(inputLines)
        simulator.particles.forEach { println(it) }
        assertThat(simulator.particles.size).isEqualTo(2)
    }

    @Test
    @Order(3)
    fun `Simulates Particle Movement`() {
        val simulator = ParticleSimulator(inputLines)
        val result = simulator.run1()
        println(result)
        assertThat(result).isEqualTo(0)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("0")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        puzzleSolver.inputData = listOf(
            "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
            "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
            "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
            "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>"
        )
        puzzleSolver.initSolver()
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("1")
    }

}
