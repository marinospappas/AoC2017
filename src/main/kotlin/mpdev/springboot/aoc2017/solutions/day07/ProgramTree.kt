package mpdev.springboot.aoc2017.solutions.day07

import mpdev.springboot.aoc2017.utils.AocException
import mpdev.springboot.aoc2017.utils.Bfs
import mpdev.springboot.aoc2017.utils.Graph

class ProgramTree(input: List<String>) {

    val weightMap = mutableMapOf<String,Int>()
    val tree = Graph<String>()
    var root: String

    init {
        processInput(input)
        root = tree.getRootId()
    }

    fun adjustOddNodeWeight(): Int {
        var current = root
        var prevDiff = 0
        while (true) {
            val weights = tree[current].getConnectedNodes().associate { it.nodeId to calculateBranchWeight(it.nodeId) }
            val (oddNodeId, oddNodeDiff) = identifyNodeOfDiffWeight(weights)
            if (oddNodeId.isNotEmpty()) {
                prevDiff = oddNodeDiff
                current = oddNodeId
                continue
            }
            return (weightMap[current] ?: 0) - prevDiff
        }
    }

    private var weight = 0
    fun addWeight(id: String) {
        weight += weightMap[id] ?: 0
    }

    fun calculateBranchWeight(nodeId: String): Int {
        weight = 0
        Bfs<String>().traverseGraph(tree[nodeId]) { id -> addWeight(id) }
        return weight
    }

    fun identifyNodeOfDiffWeight(nodeMap: Map<String,Int>): Pair<String,Int> {
        val distinctMap = nodeMap.entries.distinctBy { e -> e.value }
        if (distinctMap.size == 1)
            return Pair("", 0)
        // given that onl one item is different, then the size must be 2
        return if (nodeMap.entries.filter { it.value == distinctMap.first().value }.size == 1)
            Pair(distinctMap.first().key,
                (nodeMap[distinctMap.first().key] ?: 0) - (nodeMap[distinctMap.last().key] ?: 0)
            )
        else
            Pair(distinctMap.last().key,
                (nodeMap[distinctMap.last().key] ?: 0) - (nodeMap[distinctMap.first().key] ?: 0)
            )
    }


    fun printGraph() {
        println(Bfs<String>().graphToString(tree, tree[tree.getRootId()]))
    }

    private fun processInput(input: List<String>) {
        var thisLine = ""
        try {
            // first pass: add nodes and weights
            input.forEach { line ->
                thisLine = line
                // fwft (72) -> ktlj, cntj, xhth
                val match = Regex("""([a-z]+)[ \t]\((\d+)\).*""").find(line)
                val (name, wgt) = match!!.destructured
                weightMap[name] = Integer.parseInt(wgt)
                tree.addNode(name)
            }
            // second pass: add children
            input.forEach { line ->
                thisLine = line
                // fwft (72) -> ktlj, cntj, xhth
                val match = Regex("""([a-z]+)[ \t]\((\d+)\)(.*)""").find(line)
                val (name, wgt, chldrn) = match!!.destructured
                if (chldrn.isNotEmpty()) {
                    weightMap[name] = Integer.parseInt(wgt)
                    addChildern(name, chldrn)
                }
            }
        }
        catch (e: Exception) {
                throw AocException("bad input line $thisLine ${e.message}")
        }
    }

    private fun addChildern(node: String, chldrn: String) {
        val match = Regex(""" -> (.*)""").find(chldrn)
        try {
            val (children) = match!!.destructured
            children.split(Regex(", ")).forEach { chld ->
                tree.connect(node, chld)
            }
        } catch (e: Exception) {
            throw AocException("bad children info $chldrn ${e.message}")
        }
    }
}