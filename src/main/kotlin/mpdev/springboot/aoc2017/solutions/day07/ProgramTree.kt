package mpdev.springboot.aoc2017.solutions.day07

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mpdev.springboot.aoc2017.utils.Bfs
import mpdev.springboot.aoc2017.utils.Graph

class ProgramTree(input: List<String>) {

    val programs = Json.decodeFromString<List<Program>>(
        input.joinToString(",", "[", "]") {it.toJson()}
    )
    val weightMap = mutableMapOf<String,Int>()
    val tree = Graph<String>()
    var root: String

    init {
        processPrograms()
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

    private fun processPrograms() {
        // first pass: add nodes and weights
        programs.forEach { p ->
            weightMap[p.id] = p.weight
            tree.addNode(p.id)
        }
        // second pass: add children
        programs.forEach { p ->
            p.children.filter { it.isNotEmpty() }.forEach { c -> tree.connect(p.id, c) }
        }
    }

    companion object {
        fun String.toJson() =
            // jtbnzi (91) -> gpkrbvt, rhtin
            // {"id":"jtbnzi","weight":91,"children":["gpkrbvt","rhtin"]}
            this.replace(Regex("""^"""), """{"id":"""")
                .replace(" (", """","weight":""")
                .replace(Regex("""\)( -> )?"""), ""","children":["""")
                .replace(Regex("""$"""), """"]}""")
                .replace(Regex(", "), """","""")
    }
}

@Serializable
data class Program(val id: String, val weight: Int, val children: List<String>)