package mpdev.springboot.aoc2017.solutions.day09

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime

@Component
class Day09: PuzzleSolver() {

    final override fun setDay() {
        day = 9
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var stream: StreamOfChars

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            stream = StreamOfChars(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = stream.processStream().first
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = stream.processStream().second
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

}