package mpdev.springboot.aoc2017.solutions.day08

import kotlin.math.max

class Program(input: List<String>) {

    val instructions = mutableListOf<Instruction>().also { list ->
        input.forEach { line -> list.add(instrFromLine(line)) }
    }
    val registers = mutableMapOf<String,Int>()
    var maxValue = 0

    fun execute() {
        registers.clear()
        maxValue = 0
        instructions.forEach { instr ->
            if (instr.condition.check(getRegister(instr.condReg), instr.condParam))
                setRegister(instr.register, instr.op.execute(getRegister(instr.register), instr.parameter))
            maxValue = max(maxValue, registers.values.maxOrNull() ?: 0)
        }
    }

    private fun getRegister(reg: String) = registers[reg] ?: 0

    private fun setRegister(reg: String, value: Int) {
        registers[reg] = value
    }

    private fun instrFromLine(str: String): Instruction {
        // b inc 5 if a > -1
        val match = Regex("""([a-z]+)[ \t](inc|dec)[ \t](-?\d+)[ \t]if[ \t]([a-z]+)[ \t]([=><!]{1,2})[ \t](-?\d+)""").find(str)
        val (reg, op, param, condReg, cond, condParam) = match!!.destructured
        return Instruction(reg, Operation.fromString(op), Integer.parseInt(param),
            condReg, Condition.fromString(cond), Integer.parseInt(condParam))
    }
}