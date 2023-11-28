package mpdev.springboot.aoc2017.solutions.day16

import mpdev.springboot.aoc2017.utils.AocException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Transformation(input: List<String>) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    val transList = mutableListOf<Movement>()
    var programs = ('a'..'p').toList()

    init {
        processInput(input[0])
    }

    fun runOnce() = run(programs.toMutableList()).joinToString("")

    fun runMultiple(count: Long): String {
        val list = programs.toMutableList()
        var newCount = 0L
        for (i in 1..count) {
            run(list)
            if (list == programs) {
                log.info("state repeated - $list, $i")
                newCount = count % i
                break
            }
        }
        (1..newCount).forEach { _ -> run(list) }
        return list.joinToString("")
    }

    private fun run(list: MutableList<Char>): List<Char> {
        transList.forEach { move ->
            move.operation.execute(list, move.params)
        }
        return list
    }

    private fun processInput(input: String) {
        input.split(',').forEach { s ->
            val op = Operation.fromString(s.substring(0,1))
            val params = s.substring(1,s.length).split('/').map { x ->
                if (x.all { it.isDigit() }) Integer.parseInt(x)
                else x.first().code
            }
            transList.add(Movement(op, params))
        }
    }

    companion object {
        fun spin(list: MutableList<Char>, index: Int) {
            val l = list.size
            val newList = list.subList(l-index, l) + list.subList(0, l-index)
            list.clear()
            list.addAll(newList)
        }
        fun exchange(list: MutableList<Char>, index1: Int, index2: Int) {
            val temp = list[index1]
            list[index1] = list[index2]
            list[index2] = temp
        }
        fun partner(list: MutableList<Char>, item1: Int, item2: Int) {
            exchange(list, list.indexOf(item1.toChar()), list.indexOf(item2.toChar()))
        }
    }

    data class Movement(val operation: Operation, val params: List<Int>)
}

enum class Operation(val value: String, val execute: (MutableList<Char>, List<Int>) -> Unit) {
    SPIN("s", { l, param -> Transformation.spin(l, param[0]) }),
    EXCHANGE("x", { l, param -> Transformation.exchange(l, param[0], param[1]) }),
    PARTNER("p", { l, param -> Transformation.partner(l, param[0], param[1]) });
    companion object {
        fun fromString(str: String): Operation {
            return Operation.values().firstOrNull { it.value == str }
                ?: throw AocException("no operation found for [$str]")
        }
    }
}