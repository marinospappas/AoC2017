package mpdev.springboot.aoc2017.solutions.day19

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day19: PuzzleSolver() {

    final override fun setDay() {
        day = 19
    }

    init {
        setDay()
    }

    var result = Pair("",0)
    lateinit var tubeNetwork: TubeNetwork

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            tubeNetwork = TubeNetwork(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = tubeNetwork.walkGrid()
        }
        return PuzzlePartSolution(1, result.first, elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
        }
        return PuzzlePartSolution(2, result.second.toString(), elapsed, "milli-sec")
    }
}