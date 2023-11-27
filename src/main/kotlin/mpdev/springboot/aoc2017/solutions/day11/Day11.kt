package mpdev.springboot.aoc2017.solutions.day11

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime

@Component
class Day11: PuzzleSolver() {

    final override fun setDay() {
        day = 11
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var hexPath: HexPath

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            hexPath = HexPath(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            hexPath.getTargetPoint()
            result = hexPath.distances.last()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            hexPath.getTargetPoint()
            result = hexPath.distances.max()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }
}