package mpdev.springboot.aoc2017.solutions.day07

import mpdev.springboot.aoc2017.utils.AocException
import mpdev.springboot.aoc2017.utils.Bfs
import mpdev.springboot.aoc2017.utils.Graph

class ProgramTree(input: List<String>) {

    val weight = mutableMapOf<String,Int>()
    val tree = Graph<String>()

    init {
        processInput(input)
    }

    fun printGraph() {
        println(Bfs<String>().graphToString(tree, tree.get(tree.getRootId())))
    }

    private fun processInput(input: List<String>) {
        input.forEach { line ->
            // fwft (72) -> ktlj, cntj, xhth
            val match = Regex("""([a-z]+)[ \t]\((\d+)\)(.*)""").find(line)
            try {
                val (name, wgt, chldrn) = match!!.destructured
                weight[name] = Integer.parseInt(wgt)
                tree.addNode(name)
                if (chldrn.isNotEmpty())
                    addChildern(name, chldrn)
            } catch (e: Exception) {
                throw AocException("bad input line $line")
            }
        }
    }

    private fun addChildern(node: String, chldrn: String) {
        val match = Regex(""" -> (.*)""").find(chldrn)
        try {
            val (children) = match!!.destructured
            children.split(Regex(", ")).forEach { chld ->
                if (!tree.nodeExists(chld))
                    tree.addNode(chld)
                tree.connect(node, chld)
            }
        } catch (e: Exception) {
            throw AocException("bad children info $chldrn")
        }
    }
}