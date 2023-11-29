package mpdev.springboot.aoc2017.solutions.day18

import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import mpdev.springboot.aoc2017.solutions.day18.Instruction.*
import mpdev.springboot.aoc2017.utils.AocException

class Program(prog: List<String>, private val inputChannel: Channel<Long>, private val outputChannel: Channel<Long>,
              private val progId: Int = 0) {

    val instructionList =
        prog.map { it.split(" ") }.map { it + "" }
            .map { Triple(Instruction.fromString(it[0]), it[1], it[2]) }
    private val registers = mutableMapOf<String,Long>()
    var state = State.READY
    var sendCount = 0

    suspend fun run(exitOnReceive: Boolean) {
        var pc = 0
        registers.clear()
        sendCount = 0
        if (progId > 0)
            registers["p"] = progId.toLong()
        while (pc <= instructionList.lastIndex) {
            state = State.RUNNING
            val (instr, reg, param) = instructionList[pc]
            when (instr) {
                SET -> registers[reg] = valueOf(param)
                ADD -> registers[reg] = (registers[reg] ?: 0) + valueOf(param)
                MUL -> registers[reg] = (registers[reg] ?: 0) * valueOf(param)
                MOD -> registers[reg] = (registers[reg] ?: 0) % valueOf(param)
                JGZ -> if (valueOf(reg) > 0L) pc += valueOf(param).toInt() - 1
                SEND -> { outputChannel.send(valueOf(reg)); ++sendCount }
                RECEIVE -> when {
                    exitOnReceive -> if (valueOf(reg) != 0L) break
                    else -> { state = State.WAITING; registers[reg] = inputChannel.receive() }
                }
            }
            ++pc
        }
        state = State.STOPPED
    }

    suspend fun waitProgram(job: Job) {
        job.join()
    }

    private fun valueOf(s: String) = try {
            Integer.parseInt(s).toLong()
        }
        catch (e: Exception) { registers[s] ?: 0L }
}

enum class Instruction(val value: String) {
    SET("set"),
    ADD("add"),
    MUL("mul"),
    MOD("mod"),
    SEND("snd"),
    RECEIVE("rcv"),
    JGZ("jgz");
    companion object {
        fun fromString(str: String): Instruction {
            return Instruction.values().firstOrNull { it.value == str } ?: throw AocException("no operation found for [$str]")
        }
    }
}

enum class State { READY, RUNNING, WAITING, STOPPED }