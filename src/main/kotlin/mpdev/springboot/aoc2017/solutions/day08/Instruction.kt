package mpdev.springboot.aoc2017.solutions.day08

import mpdev.springboot.aoc2017.utils.AocException

data class Instruction(val register: String, val op: Operation, val parameter: Int, val condReg: String, val condition: Condition, val condParam: Int)

enum class Operation(val value: String, val execute: (Int,Int) -> Int) {
    INCR("inc", {a,b -> a+b}),
    DECR("dec", {a,b -> a-b});
    companion object {
        fun fromString(str: String): Operation {
            return values().firstOrNull { it.value == str }
                ?: throw AocException("no operation found for [$str]")
        }
    }
}

enum class Condition(val value: String, val check: (Int,Int) -> Boolean) {
    EQ("==", {a,b -> a == b}),
    GT(">", {a,b -> a > b}),
    GE(">=", {a,b -> a >= b}),
    LT("<", {a,b -> a < b}),
    LE("<=", {a,b -> a <= b}),
    NE("!=", {a,b -> a != b});
    companion object {
        fun fromString(str: String): Condition {
            return values().firstOrNull { it.value == str }
                ?: throw AocException("no condition found for [$str]")
        }
    }
}