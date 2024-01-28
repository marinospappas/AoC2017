package mpdev.springboot.aoc2017.solutions.day24

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day24: PuzzleSolver() {

    final override fun setDay() {
        day = 24
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var electroMagnets: ElectroMagnets

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            electroMagnets = ElectroMagnets(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            electroMagnets.findStrongestLongest()
            result = electroMagnets.strongest
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = electroMagnets.longestStrength
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}