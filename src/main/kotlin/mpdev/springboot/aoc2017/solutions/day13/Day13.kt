package mpdev.springboot.aoc2017.solutions.day13

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day13: PuzzleSolver() {

    final override fun setDay() {
        day = 13
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var firewall: Firewall

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            firewall = Firewall(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = firewall.traverseFirewall()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = firewall.findTimeToPassThrough()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}