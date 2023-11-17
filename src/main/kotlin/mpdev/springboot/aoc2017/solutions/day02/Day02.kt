package mpdev.springboot.aoc2017.solutions.day02

import mpdev.springboot.aoc2017.model.PuzzlePartSolution
import mpdev.springboot.aoc2017.solutions.PuzzleSolver
import mpdev.springboot.aoc2017.utils.AocException
import org.springframework.stereotype.Component
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureNanoTime

@Component
class Day02: PuzzleSolver() {

    final override fun setDay() {
        day = 2
    }

    init {
        setDay()
    }

    var result = 0
    lateinit var sSheet: Array<IntArray>

    override fun initSolver(): Pair<Long,String> {
        result = 0
        val elapsed = measureNanoTime {
            sSheet = Array(inputData.size) { row ->
                val cells = inputData[row].split(Regex("""[ \t]+"""))
                try {
                    IntArray(cells.size) { col -> Integer.parseInt(cells[col]) }
                } catch (e: Exception) {
                    throw AocException("Invalid input line ${inputData[row]}")
                }
            }
        }
        return Pair(elapsed/1000, "micro-sec")
    }

    override fun solvePart1(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = sSheet.sumOf { row -> row.max() - row.min() }
        }
        return PuzzlePartSolution(1, result.toString(), elapsed/1000, "micro-sec")
    }

    override fun solvePart2(): PuzzlePartSolution {
        val elapsed = measureNanoTime {
            result = sSheet.sumOf { row -> evenlyDivValues(row) }
        }
        return PuzzlePartSolution(2, result.toString(), elapsed/1000, "micro-sec")
    }

    private fun evenlyDivValues(row: IntArray): Int {
        for (i in 0..row.size - 2)
            for (j in i+1..row.size - 1) {
                val a = max(row[i], row[j])
                val b = min(row[i], row[j])
                if (a % b == 0)
                    return a / b
            }
        return 0
    }
}