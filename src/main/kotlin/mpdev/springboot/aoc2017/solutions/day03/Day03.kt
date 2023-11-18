package mpdev.springboot.aoc2017.solutions.day03

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import mpdev.springboot.aoc2017.utils.min
import org.springframework.stereotype.Component
import kotlin.math.*
import kotlin.system.measureNanoTime

@Component
class Day03: PuzzleSolver() {

    final override fun setDay() {
        day = 3
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
            result = findDistanceFromCentre(inputData[0].toInt())
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {

        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

    fun findDistanceFromCentre(x: Int): Int {
        var spiralIndx = ceil(sqrt(x.toDouble())).toInt()
        if (spiralIndx % 2 == 0)
            ++spiralIndx
        val radius = (spiralIndx - 1) / 2
        val a1 = spiralIndx * spiralIndx - radius
        val a2 = a1 - 2 * radius
        val a3 = a2 - 2 * radius
        val a4 = a3 - 2 * radius
        return radius + min(abs(a1-x), abs(a2-x), abs(a3-x), abs(a4-x))
    }
}