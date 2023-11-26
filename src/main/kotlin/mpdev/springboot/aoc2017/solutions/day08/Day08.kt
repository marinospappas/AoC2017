package mpdev.springboot.aoc2017.solutions.day08

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day08: PuzzleSolver() {

    final override fun setDay() {
        day = 8
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var program: Program

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureTimeMillis {
            program = Program(inputData)
        }
        return Pair(elapsed, "milli-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            program.execute()
            result = program.registers.values.max()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            program.execute()
            result = program.maxValue
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

}