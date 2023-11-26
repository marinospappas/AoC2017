package mpdev.springboot.aoc2017.day12

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day12.CommunicationsTree
import mpdev.springboot.aoc2017.solutions.day12.Day12
import mpdev.springboot.aoc2017.utils.Bfs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day12Test {

    private val day = 12                                     ///////// Update this for a new dayN test
    private val puzzleSolver = Day12()                      ///////// Update this for a new dayN test
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
        val t = CommunicationsTree(inputLines)
        t.printGraph()
        println(Bfs<Int>().graphToString(t.tree, t.tree[1]))
        println("graph nodes count: ${t.tree.getNodes().count()}")
        assertThat(t.tree.getNodes().count()).isEqualTo(7)
    }

    @Test
    @Order(3)
    fun `Counts Nodes`() {
        val t = CommunicationsTree(inputLines)
        val result = t.getAllChildren(0)
        println(result)
        assertThat(result.size).isEqualTo(6)
    }

    @Test
    @Order(4)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("6")
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("2")
    }
}
