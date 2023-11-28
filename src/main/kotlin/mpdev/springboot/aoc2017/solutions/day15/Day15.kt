package mpdev.springboot.aoc2017.solutions.day15

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day15: PuzzleSolver() {

    final override fun setDay() {
        day = 15
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var sequenceGenerator: SequenceGenerator

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            sequenceGenerator = SequenceGenerator(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = sequenceGenerator.countMatchingSequences1(40_000_000)
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = sequenceGenerator.countMatchingSequences2(5_000_000)
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}