package mpdev.springboot.aoc2017.solutions.day18

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Duet(input: List<String>) {

    private val channelA = Channel<Long>(Channel.UNLIMITED)
    private val channelB = Channel<Long>(Channel.UNLIMITED)
    val program1 = Program(input, channelA, channelB)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun run1(): Long {
        var result = -1L
        runBlocking {
            val job = launch { program1.run(true) }
            program1.waitProgram(job)
            while (!channelB.isEmpty)
                result = channelB.receive()
        }
        return result
    }

    val program2 = Program(input, channelB, channelA, progId = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun run2(): Long {
        runBlocking {
            val job1 = launch { program1.run(false) }
            val job2 = launch { program2.run(false) }
            while (true) {      // wait until deadlock is detected
                delay(5)
                if (program1.state != State.RUNNING && program2.state != State.RUNNING &&
                    channelA.isEmpty && channelB.isEmpty)
                    break
            }
            job1.cancel()
            job2.cancel()
        }
        return program2.sendCount.toLong()
    }
}