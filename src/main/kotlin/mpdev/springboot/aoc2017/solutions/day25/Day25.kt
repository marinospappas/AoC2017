package mpdev.springboot.aoc2017.solutions.day25

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day25: PuzzleSolver() {

    final override fun setDay() {
        day = 25
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var diagnostic: Diagnostic

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            diagnostic = Diagnostic(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = diagnostic.execute()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        return PuzzlePartSolution(2, "Completed", 0, "milli-sec")
    }
}