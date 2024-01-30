package mpdev.springboot.aoc2017.solutions.day21

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day21: PuzzleSolver() {

    final override fun setDay() {
        day = 21
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var patternEnhancer: PatternEnhancerV2

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            patternEnhancer = PatternEnhancerV2(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = patternEnhancer.solve1()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = patternEnhancer.solve2()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}