package mpdev.springboot.aoc2017.solutions.day12

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureTimeMillis

@Component
class Day12: PuzzleSolver() {

    final override fun setDay() {
        day = 12
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var tree: CommunicationsTree

    override fun initSolver(): Pair<Long,String> {
        val elapsed = measureTimeMillis {
            tree = CommunicationsTree(inputData)
        }
        return Pair(elapsed, "milli-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = tree.getAllChildren(0).size
        }
        return PuzzlePartSolution(1, result.toString(), elapsed, "milli-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureTimeMillis {
            result = tree.getAllGroups().size
        }
        return PuzzlePartSolution(2, result.toString(), elapsed, "milli-sec")
    }

}