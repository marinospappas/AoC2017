package mpdev.springboot.aoc2017.solutions.day25

import mpdev.springboot.aoc2017.utils.AocException


class Diagnostic(input: List<String>) {

    var tape = mutableMapOf<Int,Int>()
    val states = mutableMapOf<String,State>()
    var startState: String = ""
    var countSteps: Int = 0

    init {
        processInput(input)
    }

    fun execute(): Int {
        var index = 0
        var curState = startState
        repeat(countSteps) {
            val action = states[curState]?.action ?: throw AocException("could not retrieve state $curState")
            val curValue = tape[index] ?: 0
            val (newValue, indexIncr, newState) = action[curValue]
            tape[index] = newValue
            index += indexIncr
            curState = newState
        }
        return tape.values.count { it == 1 }
    }

    private fun processInput(input: List<String>) {
        val input1 = input.joinToString("|").split("||")
        input1.forEach { line ->
            when {
                line.startsWith("Begin") -> {
                    val match = Regex("""Begin in state ([A-Z]).+ diagnostic checksum after (\d+) steps""").find(line)
                    val (init, count) = match!!.destructured
                    startState = init
                    countSteps = count.toInt()
                }
                line.startsWith("In state") -> {
                    val match = Regex("""In state ([A-Z]).+ current value is (\d).+ Write the value (\d).+ Move one slot to the (right|left).+ Continue with state ([A-Z]).+ current value is (\d).+ Write the value (\d).+ Move one slot to the (right|left).+ Continue with state ([A-Z]).""").find(line)
                    val (state, _, newVal1, move1, newState1, _, newVal2, move2, newState2) = match!!.destructured
                    states[state] = State(state, listOf(
                        Triple(newVal1.toInt(), if (move1 == "left") -1 else 1, newState1),
                        Triple(newVal2.toInt(), if (move2 == "left") -1 else 1, newState2),
                    ))
                }
            }
        }
    }
}

data class State(val id: String, val action: List<Triple<Int,Int,String>>)