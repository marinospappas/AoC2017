package mpdev.springboot.aoc2017.solutions.day15

class SequenceGenerator(input: List<String>) {

    val startA: Long
    val startB: Long
    val factorA = 16807L
    val factorB = 48271L
    val divider = 2147483647L

    init {
        startA = Integer.parseInt(input[0].replace(Regex("""^Gen.*with """), "")).toLong()
        startB = Integer.parseInt(input[1].replace(Regex("""^Gen.*with """), "")).toLong()
    }

    fun countMatchingSequences1(repeat: Int): Int {
        var countMatching = 0
        var a = startA
        var b = startB
        (1 .. repeat).forEach { _ ->
            a = (a * factorA) % divider
            b = (b * factorB) % divider
            if (a.and(0xFFFF) == b.and(0xFFFF))
                ++countMatching
        }
        return countMatching
    }

    fun countMatchingSequences2(repeat: Int): Int {
        var countMatching = 0
        var compCount = 0
        var a = startA
        var b = startB
        val queueA = ArrayDeque<Long>()
        val queueB = ArrayDeque<Long>()
        while (true) {
            a = (a * factorA) % divider
            b = (b * factorB) % divider
            if (a % 4L == 0L)
                queueA.add(a)
            if (b % 8L == 0L)
                queueB.add(b)
            if (queueA.isNotEmpty() && queueB.isNotEmpty()) {
                ++compCount
                if (queueA.removeFirst().and(0xFFFF) == queueB.removeFirst().and(0xFFFF))
                    ++countMatching
                if (compCount >= repeat)
                    break
            }
        }
        return countMatching
    }
}