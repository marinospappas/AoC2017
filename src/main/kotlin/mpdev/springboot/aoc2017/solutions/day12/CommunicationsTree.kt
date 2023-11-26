package mpdev.springboot.aoc2017.solutions.day12

import mpdev.springboot.aoc2017.utils.AocException
import mpdev.springboot.aoc2017.utils.Bfs
import mpdev.springboot.aoc2017.utils.Graph

class CommunicationsTree(input: List<String>) {

    val tree = Graph<Int>()
    var root = 0

    init {
        processInput(input)
    }

    private var nodes = mutableSetOf<Int>()
    fun getAllChildren(nodeId: Int): Set<Int> {
        nodes = mutableSetOf(nodeId)
        Bfs<Int>().traverseGraph(tree[nodeId]) { id -> nodes.add(id) }
        return nodes
    }

    fun getAllGroups(): Set<Set<Int>> {
        val groups = mutableSetOf<Set<Int>>()
        tree.getNodes().keys.mapTo(groups) { node -> getAllChildren(node) }
        return groups
    }

    fun printGraph() {
        println(Bfs<Int>().graphToString(tree, tree[root]))
    }

    private fun processInput(input: List<String>) {
        var thisLine = ""
        try {
            // first pass: add nodes
            input.forEach { line ->
                thisLine = line
                // 4 <-> 2, 3, 6
                val match = Regex("""(\d+) <-> .*""").find(line)
                val (name) = match!!.destructured
                tree.addNode(Integer.parseInt(name))
            }
            // second pass: add children
            input.forEach { line ->
                thisLine = line
                // 4 <-> 2, 3, 6
                val match = Regex("""(\d+) <-> (.*)""").find(line)
                val (name, chldrn) = match!!.destructured
                addChildern(Integer.parseInt(name), chldrn)
            }
        }
        catch (e: Exception) {
                throw AocException("bad input line $thisLine ${e.message}")
        }
    }

    private fun addChildern(node: Int, children: String) {
        try {
            children.split(Regex(", ")).forEach { chld ->
                tree.connect(node, Integer.parseInt(chld))
            }
        } catch (e: Exception) {
            throw AocException("bad children info $children ${e.message}")
        }
    }
}