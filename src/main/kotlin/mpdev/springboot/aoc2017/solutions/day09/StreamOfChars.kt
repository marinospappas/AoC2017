package mpdev.springboot.aoc2017.solutions.day09

class StreamOfChars(input: List<String>) {

    private val BEGIN_GROUP = '{'
    private val END_GROUP = '}'
    private val BEGIN_GRBG = '<'
    private val END_GRBG = '>'
    private val SKIP_NEXT = '!'

    var chars = input[0].toCharArray()

    fun processStream(): Pair<Int,Int> {
        var sumOfScores = 0
        var garbageCount = 0
        var level = 0
        var skipNext = false
        var garbage = false
        chars.forEach { c ->
            if (skipNext) {
                skipNext = false
                return@forEach
            }
            if (c == SKIP_NEXT) {
                skipNext = true
                return@forEach
            }
            if (garbage) {
                if (c == END_GRBG)
                    garbage = false
                else
                    ++garbageCount
                return@forEach
            }
            when (c) {
                BEGIN_GROUP -> ++level
                END_GROUP -> { sumOfScores += level; --level }
                BEGIN_GRBG -> garbage = true
            }
        }
        return Pair(sumOfScores,garbageCount)
    }

}