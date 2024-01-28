package mpdev.springboot.aoc2017.solutions.day22

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day22: PuzzleSolver() {

    final override fun setDay() {
        day = 22
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var virus: Virus

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            virus = Virus(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = virus.solve1(10000)
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            virus = Virus(inputData)
            result = virus.solve2(10000000)
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}