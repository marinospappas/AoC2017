package mpdev.springboot.aoc2017.solutions.day07

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import org.springframework.stereotype.Component
import kotlin.system.measureNanoTime

@Component
class Day07: PuzzleSolver() {

    final override fun setDay() {
        day = 7
    }

    init {
        setDay()
    }

    var result = ""
    lateinit var programTree: ProgramTree

    override fun initSolver(): Pair<Long,String> {
        result = ""
        val elapsed = measureNanoTime {
            programTree = ProgramTree(inputData)
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = programTree.tree.getRootId()
        }
        return PuzzlePartSolution(1, result, elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = programTree.adjustOddNodeWeight().toString()
        }
        return PuzzlePartSolution(2, result, elapsed/1000, "micro-sec")
    }

}