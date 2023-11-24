package mpdev.springboot.aoc2017.solutions.day05

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import mpdev.springboot.aoc2017.utils.isAnagram
import mpdev.springboot.aoc2017.utils.min
import org.springframework.stereotype.Component
import kotlin.math.*
import kotlin.system.measureNanoTime

@Component
class Day05: PuzzleSolver() {

    final override fun setDay() {
        day = 5
    }

    init {
        setDay()
    }

    var result = 0

    override fun initSolver(): Pair<Long,String> {
        result = 0
        val elapsed = measureNanoTime {
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {

        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {

        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

}