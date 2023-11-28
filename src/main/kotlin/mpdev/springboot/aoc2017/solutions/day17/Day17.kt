package mpdev.springboot.aoc2017.solutions.day17

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day17: PuzzleSolver() {

    final override fun setDay() {
        day = 17
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var spinlock: Spinlock

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            spinlock = Spinlock(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = spinlock.fillBuffer1(2017)
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = spinlock.fillBuffer2(50_000_000)
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}