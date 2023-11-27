package mpdev.springboot.aoc2017.day13

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day13.Day13
import mpdev.springboot.aoc2017.solutions.day13.Firewall
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day13Test {

    private val day = 13                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day13()                      ///////// Update this for a new dayN test
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
        val firewall = Firewall(inputLines)
        println(firewall.scanners)
        assertThat(firewall.scanners.size).isEqualTo(4)
    }

    @Test
    @Order(3)
    fun `Calculates Scanner Position`() {
        val firewall = Firewall(listOf(
            "0: 4", "1: 4", "2: 3"
        ))
        val expected = intArrayOf(0,1,2,3,2,1,0,1,2,3,2,1)
        val expected1 = intArrayOf(0,1,2,1,0,1,2,1,0,1,2,1)
        for (t in 0..11) {
            val pos = firewall.getScannerPosition(1, t)
            val pos1 = firewall.getScannerPosition(2, t)
            println("$pos  $pos1")
            assertThat(pos).isEqualTo(expected[t])
            assertThat(pos1).isEqualTo(expected1[t])
        }
    }

    @Test
    @Order(4)
    fun `Traverses Firewall`() {
        val firewall = Firewall(inputLines)
        val result = firewall.traverseFirewall()
        println(result)
        assertThat(result).isEqualTo(24)
    }

    @Test
    @Order(5)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("24")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("10")
    }

}
