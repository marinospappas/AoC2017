package mpdev.springboot.aoc2017.solutions.day24

import kotlin.math.max

class ElectroMagnets(input: List<String>) {

    val components = input.map { line -> line.split('/') }.map { Port(it[0].toInt(), it[1].toInt()) }

    var strongest = 0
    var longest = 0
    var longestStrength = 0

    fun findStrongestLongest(port: Int = 0, length: Int = 0, strength: Int = 0) {
        strongest = max(strength, strongest)
        if (length > longest) {
            longest = length
            longestStrength = strength
        }
        else if (length == longest) {
            longestStrength = max(strength, longestStrength)
        }
        for (c in components) {
            if (!c.used && (c.left == port || c.right == port)) {
                c.used = true
                findStrongestLongest(
                    if (c.left == port) c.right else c.left,
                    length + 1, strength + c.strength()
                )
                c.used = false
            }
        }
    }
}

data class Port(val left: Int, val right: Int, var used: Boolean = false) {
    fun strength() = left + right
}
