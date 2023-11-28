package mpdev.springboot.aoc2017.solutions.day17

import mpdev.springboot.aoc2017.utils.CircularList

class Spinlock(input: List<String>) {

    val increment = Integer.parseInt(input[0])

    val buffer = CircularList<Int>(mutableListOf())

    fun fillBuffer1(endValue: Int): Int {
        buffer.insert(0, 0)
        repeat (endValue) {
            buffer.incrCurPos(increment)
            buffer.insert(buffer.curPos+1, it+1)
        }
        return buffer[buffer.curPos+1]
    }

    fun fillBuffer2(endValue: Int): Int {
        var index = 0
        var result = 0
        (1..endValue).forEach { i ->           // i is the value to be inserted
            index = ((index + increment) % i) + 1   // but it's not actually inserted - just the index is tracked
            if(index == 1)                          // if index is 1 (i.e. just after '0' then keep this value
                result = i
        }
        return result
    }
}