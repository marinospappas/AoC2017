package mpdev.springboot.aoc2017.solutions.day06

class Memory(input: List<String>) {

    val memBanks = input[0].split(Regex("""[\t ]+""")).map { Integer.parseInt(it) }

    fun rearrangeMemoryAndDetectLoop(): Pair<Int,Int> {
        val memory = memBanks.toMutableList()
        val states = mutableSetOf<List<Int>>()
        do {
            states.add(memory.toList())
            reDestributeBlocks(memory)
        } while (!states.contains(memory))
        return Pair(states.size, states.size - states.indexOf(memory))
    }

    private fun reDestributeBlocks(banks: MutableList<Int>) {
        var index = banks.indexOf(banks.max())
        val blocks = banks[index]
        banks[index] = 0
        for (i in 1..blocks) {
            ++index
            if (index > banks.lastIndex)
                index = 0
            ++banks[index]
        }
    }
}
