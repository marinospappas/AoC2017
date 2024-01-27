package mpdev.springboot.aoc2017.solutions.day23

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mpdev.springboot.aoc2017.utils.isPrime

class CoProcessor(input: List<String>) {

    private val channelA = Channel<Long>(Channel.UNLIMITED)
    private val channelB = Channel<Long>(Channel.UNLIMITED)
    val program1 = Program23(input, channelA, channelB)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun run1(): Long {
        var result = -1L
        runBlocking {
            val job = launch { program1.run(true) }
            program1.waitProgram(job)
            if (!channelB.isEmpty)
                result = channelB.receive()
        }
        return result
    }

    // optimised assembly program
    fun part2(): Long {
        var b = 0
        var c = 0
        var d = 0
        var f = 0
        var g = 0
        var h = 0L

        b = 84 * 100 - (-100000)
        c = b
        c -= -17000
        do {
            f = 1
            d = 2
            while (d < b) {
                if (b % d == 0) {
                    f = 0
                    break
                }
                d++
            }
            if (f == 0) {
                h++
            }
            g = b - c
            b += 17
        } while (g != 0)
        return h
    }

}