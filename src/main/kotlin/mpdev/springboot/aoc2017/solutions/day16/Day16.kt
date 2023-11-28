package mpdev.springboot.aoc2017.solutions.day16

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day16: PuzzleSolver() {

    final override fun setDay() {
        day = 16
    }

    init {
        setDay()
    }

    var result = ""
    lateinit var transformation: Transformation

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            transformation = Transformation(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = transformation.runOnce()
        }
        return PuzzlePartSolution(1, result, elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = transformation.runMultiple(1000_000_000)
        }
        return PuzzlePartSolution(2, result, elapsed, "milli-sec")
    }
}