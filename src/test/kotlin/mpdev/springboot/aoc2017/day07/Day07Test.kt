package mpdev.springboot.aoc2017.day07

import mpdev.springboot.aoc2017.input.InputDataReader
import mpdev.springboot.aoc2017.solutions.day07.Day07
import mpdev.springboot.aoc2017.solutions.day07.ProgramTree
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        val root = t.root
        println("root: $root")
        t.printGraph()
        println("graph nodes count: ${t.tree.getNodes().count()}")
        println("weight map count: ${t.weightMap.count()}")
        println("total weight: ${t.weightMap.values.sum()}")
        assertThat(root).isEqualTo("tknk")
    }

    @Test
    @Order(3)
    fun `Solves Part 1`() {
        assertThat(puzzleSolver.solvePart1().result).isEqualTo("tknk")
    }

    @ParameterizedTest
    @MethodSource("diffWeightArguments")
    @Order(4)
    fun `Identifies Branch of diff weight`(input: Map<String,Int>, expectedId: String, expectedDiff: Int) {
        val t = ProgramTree(inputLines)
        val (id, diff) = t.identifyNodeOfDiffWeight(input)
        assertThat(id).isEqualTo(expectedId)
        assertThat(diff).isEqualTo(expectedDiff)
    }

    @Test
    @Order(5)
    fun `Calculates Branch Weight`() {
        val t = ProgramTree(inputLines)
        val totalWeight = t.calculateBranchWeight(t.root)
        println("total weight: ${totalWeight}")
        assertThat(totalWeight).isEqualTo(778)
        for (id in t.tree[t.root].getConnectedNodes().map { it.nodeId }) {
            val weight = t.calculateBranchWeight(id)
            println("$id weight: ${weight}")
            assertThat(weight).isEqualTo(
                if (id == "ugml") 251
                else 243
            )
        }
    }

    @Test
    @Order(6)
    fun `Calculates Odd Node Adjustment`() {
        val t = ProgramTree(inputLines)
        val result = t.adjustOddNodeWeight()
        println("new weight: $result")
        assertThat(result).isEqualTo(60)
    }

    @Test
    @Order(7)
    fun `Solves Part 2`() {
        assertThat(puzzleSolver.solvePart2().result).isEqualTo("60")
    }

    private fun diffWeightArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(mapOf(Pair("abc", 10),Pair("efg", 10),Pair("jkl", 10),Pair("xyz", 10)), "", 0),
            Arguments.of(mapOf(Pair("abc", 10),Pair("efg", 10),Pair("jkl", 12),Pair("xyz", 10)), "jkl", 2),
            Arguments.of(mapOf(Pair("abc", 10),Pair("efg", 10),Pair("jkl", 7),Pair("xyz", 10)), "jkl", -3)
        )
    }
}
