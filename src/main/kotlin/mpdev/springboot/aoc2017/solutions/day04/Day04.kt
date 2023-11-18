package mpdev.springboot.aoc2017.solutions.day04

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import mpdev.springboot.aoc2017.utils.isAnagram
import mpdev.springboot.aoc2017.utils.min
import org.springframework.stereotype.Component
import kotlin.math.*
import kotlin.system.measureNanoTime

@Component
class Day04: PuzzleSolver() {

    final override fun setDay() {
        day = 4
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var passphrases: List<List<String>>

    override fun initSolver(): Pair<Long,String> {
        result = 0
        val elapsed = measureNanoTime {
            passphrases = inputData.map { line -> line.split(" ") }
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = passphrases.count { isValid(it) }
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = passphrases.count { isValid2(it) }
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

    fun isValid(pass: List<String>): Boolean = pass.size == pass.distinct().size

    fun isValid2(pass: List<String>): Boolean {
        for (i in 0 .. pass.lastIndex-1)
            for (j in i+1 .. pass.lastIndex)
                if (pass[i].isAnagram(pass[j]))
                    return false
        return true
    }

}