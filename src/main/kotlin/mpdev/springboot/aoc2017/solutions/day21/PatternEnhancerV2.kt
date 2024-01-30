package mpdev.springboot.aoc2017.solutions.day21

import java.util.*
import kotlin.math.sqrt

class PatternEnhancerV2(input: List<String>) {

    private var rules: MutableList<Array<BooleanArray>> = mutableListOf()
    private var enhancements: MutableList<Array<BooleanArray>> = mutableListOf()
    private var part1count = 0
    private var part2count = 0

    var grid = arrayOf(
        booleanArrayOf(false, true, false),
        booleanArrayOf(false, false, true),
        booleanArrayOf(true, true, true)
    )

    init {
        parse(input)
    }

    private fun flipH(box: Array<BooleanArray>) {
        for (i in box.indices) {
            for (j in 0 until box[0].size / 2) {
                val temp = box[i][box[0].size - j - 1]
                box[i][box[0].size - j - 1] = box[i][j]
                box[i][j] = temp
            }
        }
    }

    private fun flipV(box: Array<BooleanArray>) {
        var temp: BooleanArray
        for (i in 0 until box.size / 2) {
            temp = box[box.size - i - 1]
            box[box.size - i - 1] = box[i]
            box[i] = temp
        }
    }

    private fun rotate(box: Array<BooleanArray>): Array<BooleanArray> {
        val temp = Array(box.size) {
            BooleanArray(
                box.size
            )
        }
        for (i in box.indices) {
            for (j in box[i].indices) {
                temp[j][i] = box[i][j]
            }
        }
        return temp
    }

    private fun matches(box: Array<BooleanArray>?, rule: Array<BooleanArray>): Boolean {
        return Arrays.deepEquals(box, rule)
    }

    private fun match(box: Array<BooleanArray>, rule: Array<BooleanArray>): Array<BooleanArray> {
        var thisBox = box
        if (matches(thisBox, rule)) return rule
        flipH(thisBox)
        if (matches(thisBox, rule)) return rule
        thisBox = rotate(thisBox)
        if (matches(thisBox, rule)) return rule
        flipV(thisBox)
        if (matches(thisBox, rule)) return rule
        flipH(thisBox)
        if (matches(thisBox, rule)) return rule
        flipV(thisBox)
        if (matches(thisBox, rule)) return rule
        thisBox = rotate(thisBox)
        if (matches(thisBox, rule)) return rule
        flipH(thisBox)
        return if (matches(thisBox, rule)) rule else emptyArray()
    }

    private fun enhance() {
        repeat (18) {
            println("count: " + grid.sumOf { a -> a.count { t -> t } })
            val div = if (grid.size % 2 == 0) 2 else 3
            val subs = grid.size / div * (grid.size / div)
            val newSize = grid.size / div * (div + 1)
            val temp = copy(grid)
            grid = Array(newSize) { BooleanArray(newSize) }
            for (i in 0 until subs) {
                var e = getSub(temp, i)
                e = getEnhancement(e)
                putSub(e, i)
            }
            if (it == 4) part1count = grid.sumOf { a -> a.count { t -> t } }
        }
        part2count = grid.sumOf { a -> a.count { t -> t } }
    }

    private fun getSub(box: Array<BooleanArray>, index: Int): Array<BooleanArray> {
        val len = if (box.size % 2 == 0) 2 else 3
        val divs = box.size / len
        val temp = Array(len) { BooleanArray(len) }
        for (i in 0 until len) {
            for (j in 0 until len) {
                val y = i + index % divs * len
                val x = j + index / divs * len
                temp[i][j] = box[y][x]
            }
        }
        return temp
    }

    private fun putSub(box: Array<BooleanArray>?, index: Int) {
        val divs = grid.size / box!!.size
        for (i in box.indices) {
            for (j in box.indices) {
                val y = i + index % divs * box.size
                val x = j + index / divs * box.size
                grid[y][x] = box[j][i]
            }
        }
    }

    private fun copy(box: Array<BooleanArray>): Array<BooleanArray> {
        val copy = Array(box.size) { BooleanArray(box.size) }
        for (i in box.indices) {
            for (j in box.indices) {
                copy[i][j] = box[i][j]
            }
        }
        return copy
    }

    private fun getEnhancement(box: Array<BooleanArray>): Array<BooleanArray> {
        for (i in rules.indices) {
            var temp: Array<BooleanArray> = copy(box)
            temp = match(temp, rules[i])
            if (temp.isNotEmpty()) {
                return enhancements[i]
            }
        }
        return emptyArray()
    }

    fun solve1(): Int {
        enhance()
        return part1count
    }

    fun solve2(): Int {
        return part2count
    }

    private fun parse(input: List<String>) {
        for (line in input) {
            val split = line.split(" => ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (indx in 0..1) {
                split[indx] = split[indx].replace("/", "")
                val size = sqrt(split[indx].length.toDouble()).toInt()
                val temp = Array(size) { BooleanArray(size) }
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        temp[i][j] = split[indx][i * size + j] == '#'
                    }
                }
                if (indx == 0) rules.add(temp) else enhancements.add(temp)
            }
        }
    }
}