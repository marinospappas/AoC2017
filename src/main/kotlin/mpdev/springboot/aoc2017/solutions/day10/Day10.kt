package mpdev.springboot.aoc2017.solutions.day10

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime

@Component
class Day10: PuzzleSolver() {

    final override fun setDay() {
        day = 10

    }

    init {
        setDay()
    }

    var result = 0
    lateinit var knotHash: KnotHash

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            knotHash = KnotHash(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            val hash = knotHash.hash1()
            result = hash[0] * hash[1]
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val resultP2: String
        val elapsed = measureNanoTime {
            val sHash = knotHash.sparseHash()
            resultP2 = knotHash.denseHash(sHash)
        }
        return PuzzlePartSolution(2, resultP2, elapsed/1000, "micro-sec")
    }

}