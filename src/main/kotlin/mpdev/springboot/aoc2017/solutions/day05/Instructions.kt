package mpdev.springboot.aoc2017.solutions.day05

class Instructions(input: List<String>) {

    var jumpOffsets = input.map { Integer.parseInt(it) }

    fun executeJumpSeries1(): Int {
        var steps = 0
        var index = 0
        var newIndex: Int
        val offsets = jumpOffsets.toMutableList()
        while (true) {
            newIndex = index + offsets[index]
            ++steps
            if (newIndex > offsets.lastIndex)
                return steps
            ++offsets[index]
            index = newIndex
        }
    }

    fun executeJumpSeries2(): Int {
        var steps = 0
        var index = 0
        var newIndex: Int
        val offsets = jumpOffsets.toMutableList()
        while (true) {
            newIndex = index + offsets[index]
            ++steps
            if (newIndex > offsets.lastIndex)
                return steps
            if (newIndex - index >= 3)
                --offsets[index]
            else
                ++offsets[index]
            index = newIndex
        }
    }
}