package mpdev.springboot.aoc2017.day07

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day07.Day07
import mpdev.springboot.aoc2017.solutions.day07.ProgramTree
import mpdev.springboot.aoc2017.utils.Bfs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day07Test {

    private val day = 7                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day07()                      ///////// Update this for a new dayN test
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
        val t = ProgramTree(inputLines)
        println(t.tree.getRootId())
        assertThat(puzzleSolver.day).isEqualTo(day)
    }

    @Test
    @Order(2)
    fun `Finds Root of Graph`() {
        val t = ProgramTree(inputLines)
        val root = t.tree.getRootId()
        println("root= $root")
        println(t.printGraph())
        assertThat(root).isEqualTo("tknk")
    }

    @Test
    @Order(2)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("tknk")
    }

    @Test
    @Order(4)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("3")
    }
}
