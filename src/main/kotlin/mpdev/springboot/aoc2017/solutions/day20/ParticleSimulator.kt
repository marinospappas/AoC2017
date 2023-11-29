package mpdev.springboot.aoc2017.solutions.day20

import mpdev.springboot.aoc2017.utils.PointND

class ParticleSimulator(input: List<String>) {

    val particles = mutableMapOf<Int,Particle>()
    val point0 = PointND(IntArray(3){0})

    init {
        processInput(input)
    }

    fun run1(): Int {
        val minAcceleration = particles.entries.filter { // find the particles with min acceleration
            it.value.acceleration == particles.values.minBy { p -> p.acceleration.manhattan(point0) }.acceleration
        }
        return if (minAcceleration.size == 1)    // if there is only one then this is it
            minAcceleration.first().key
        else {                                   // else run simulation and find the closest particle
            val partMap = particles.toMutableMap()
            repeat(1000) { move(partMap) }
            particles.entries.minBy { e -> e.value.position.manhattan(point0) }.key
        }
    }

    fun run2(): Int {
        val partMap = particles.toMutableMap()
        repeat(1000) {
            move(partMap)
            val collisions = partMap.entries.groupBy { it.value.position }
                .entries.map { it.value }
                .filter { it.size > 1 }
                .flatten()
                .map { it.key }
            collisions.forEach { k -> partMap.remove(k) }
        }
        return partMap.size
    }

    private fun move(parts: MutableMap<Int,Particle>) {
        parts.values.forEach { p ->
            p.velocity += p.acceleration
            p.position += p.velocity
        }
    }

    private fun processInput(input: List<String>) {
        for (i in input.indices) {
            // p=<-1488,-2830,-496>, v=<-212,-404,-70>, a=<14,27,4>
            val match = Regex("""p=<(-?\d+,-?\d+,-?\d+)>, v=<(-?\d+,-?\d+,-?\d+)>, a=<(-?\d+,-?\d+,-?\d+)>""").find(input[i])
            val (pos, vel, acc) = match!!.destructured
            particles[i] = Particle(
                PointND(pos.split(',').map { Integer.parseInt(it) }.toIntArray()),
                PointND(vel.split(',').map { Integer.parseInt(it) }.toIntArray()),
                PointND(acc.split(',').map { Integer.parseInt(it) }.toIntArray())
            )
        }
    }
}

data class Particle(var position: PointND, var velocity: PointND, val acceleration: PointND)