package mpdev.springboot.aoc2017.solutions.day01

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime

@Component
class Day01: PuzzleSolver() {

    final override fun setDay() {
        day = 1
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var intList: IntArray

    override fun initSolver(): Pair<Long,String> {
        result = 0
        val elapsed = measureNanoTime {
            val chars = inputData[0].toCharArray()
            intList = IntArray(inputData[0].length) { chars[it].digitToInt() }
            log.info("configured int array, length = ${intList.size}")
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = calculateSumOfMatchingItems(1)
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = calculateSumOfMatchingItems(intList.size / 2)
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

    fun calculateSumOfMatchingItems(indexIncr: Int): Int {
        var sum = 0
        for (i in intList.indices) {
            val j = (i + indexIncr) % intList.size
            if (intList[i] == intList[j])
                sum += intList[i]
        }
        return sum
    }
}