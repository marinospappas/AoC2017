package mpdev.springboot.aoc2017.solutions.day23

import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import mpdev.springboot.aoc2017.solutions.day23.Instruction.*
import mpdev.springboot.aoc2017.utils.AocException

class Program23(prog: List<String>, private val inputChannel: Channel<Long>, private val outputChannel: Channel<Long>,
              private val progId: Int = 0) {

    val instructionList =
        prog.filter { it.isNotEmpty() }
            .map { it.split(" ") }.map { it + "" }
            .map { Triple(Instruction.fromString(it[0]), it[1], it[2]) }
    val registers = mutableMapOf<String,Long>()
    var state = State.READY
    var sendCount = 0
    var debug = false

    suspend fun run(exitOnReceive: Boolean) {
        var mulCount = 0L
        var pc = 0
        registers.clear()
        sendCount = 0
        if (progId > 0)
            registers["p"] = progId.toLong()
        while (pc <= instructionList.lastIndex) {
            state = State.RUNNING
            if (debug) printDebug(pc)
            if (debug && pc == 10) break
            val (instr, reg, param) = instructionList[pc]
            when (instr) {
                SET -> registers[reg] = valueOf(param)
                ADD -> registers[reg] = (registers[reg] ?: 0) + valueOf(param)
                SUB -> registers[reg] = (registers[reg] ?: 0) - valueOf(param)
                MUL -> {
                    registers[reg] = (registers[reg] ?: 0) * valueOf(param)
                    ++mulCount
                }
                MOD -> registers[reg] = (registers[reg] ?: 0) % valueOf(param)
                JGZ -> if (valueOf(reg) > 0L) pc += valueOf(param).toInt() - 1
                JNZ -> if (valueOf(reg) != 0L) pc += valueOf(param).toInt() - 1
                SEND -> { outputChannel.send(valueOf(reg)); ++sendCount }
                RECEIVE -> when {
                    exitOnReceive -> if (valueOf(reg) != 0L) break
                    else -> { state = State.WAITING; registers[reg] = inputChannel.receive() }
                }
                NOP -> {}
            }
            ++pc
        }
        state = State.STOPPED
        outputChannel.send(mulCount)
        if (debug) printDebug(pc)
    }

    fun printDebug(pc: Int) {
        println("state: $state, pc: $pc, registers: $registers")
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
    SUB("sub"),
    MUL("mul"),
    MOD("mod"),
    SEND("snd"),
    RECEIVE("rcv"),
    JGZ("jgz"),
    JNZ("jnz"),
    NOP("nop");
    companion object {
        fun fromString(str: String): Instruction {
            return Instruction.values().firstOrNull { it.value == str } ?: throw AocException("no operation found for [$str]")
        }
    }
}

enum class State { READY, RUNNING, WAITING, STOPPED }