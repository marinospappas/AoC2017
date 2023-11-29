package mpdev.springboot.aoc2017.solutions.day20

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@Component
class Day20: PuzzleSolver() {

    final override fun setDay() {
        day = 20
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var particleSimulator: ParticleSimulator

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureNanoTime {
            particleSimulator = ParticleSimulator(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = particleSimulator.run1()
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = particleSimulator.run2()
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }
}