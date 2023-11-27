package mpdev.springboot.aoc2017.solutions.day13

import mpdev.springboot.aoc2017.utils.AocException

class Firewall(input: List<String>) {

    val scanners = input.map { it.split(Regex(": ")) }.associate { Integer.parseInt(it[0]) to Integer.parseInt(it[1]) }

    fun traverseFirewall() =
        scanners.entries.sumOf { scanner ->
            if (scanner.key % (2 * (scanner.value - 1)) == 0) scanner.key * scanner.value
            else 0
        }

    fun findTimeToPassThrough(): Int {
        var startTime = 0
        while (scanners.entries.any { scanner -> (scanner.key + startTime) % (2 * (scanner.value - 1)) == 0 }) {
            startTime++
        }
        return startTime
    }

    fun getScannerPosition(index: Int, t: Int): Int {       // not used any more as more efficient solution was found
        val range = scanners[index] ?: throw AocException("no scanner in depth $index")
        return if (t % (2*(range-1)) < range) t % (2*(range-1)) else 2*(range-1) - (t % (2*(range-1)))
    }
}