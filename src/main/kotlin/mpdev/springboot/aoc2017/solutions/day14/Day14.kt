package mpdev.springboot.aoc2017.solutions.day14

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day14: PuzzleSolver() {

    final override fun setDay() {
        day = 14
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var diskDefrag: DiskDefragmenter

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            diskDefrag = DiskDefragmenter(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = diskDefrag.countUsed()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = diskDefrag.countGroups()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}