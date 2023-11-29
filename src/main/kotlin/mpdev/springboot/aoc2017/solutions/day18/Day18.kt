package mpdev.springboot.aoc2017.solutions.day18

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day18: PuzzleSolver() {

    final override fun setDay() {
        day = 18
    }

    init {
        setDay()
    }

    var result = 0L
    lateinit var duet: Duet

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            duet = Duet(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = duet.run1()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = duet.run2()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}