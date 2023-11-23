package mpdev.springboot.aoc2017.solutions.day03

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
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
    lateinit var spiral: Spiral

    override fun initSolver(): Pair<Long,String> {
        result = 0
        val elapsed = measureNanoTime {
            spiral = Spiral(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = spiral.findDistanceFromCentre(spiral.inputValue)
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = spiral.fillSpiral()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

}