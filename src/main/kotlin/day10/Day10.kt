package day10

import java.util.LinkedList
import java.util.Queue

@OptIn(ExperimentalUnsignedTypes::class)
data class Data(val lightDiagram: String, val buttons: List<List<Int>>, val joltage: UByteArray)

@OptIn(ExperimentalUnsignedTypes::class)
class Day10 {
    fun part1(input: List<Data>): Int {
        fun String.compress(): Int {
            var result = 0
            for ((i, c) in this.withIndex()) {
                if (c == '#') {
                    result = result or (1 shl i)
                }
            }
            return result
        }

        fun List<List<Int>>.compress(): List<Int> = map { it.fold(0) { acc, num -> acc or (1 shl num) } }

        fun bfs(target: Int, transitions: List<Int>): Int {
            val start = 0
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            queue.add(start to 0)
            val visited = hashSetOf(start)
            while (queue.isNotEmpty()) {
                val (from, distance) = queue.poll()
                if (from == target) return distance
                for (transition in transitions) {
                    val to = from xor transition
                    if (to !in visited) {
                        visited.add(to)
                        queue.add(to to distance + 1)
                    }
                }
            }
            error("Should not reach here")
        }

        return input.sumOf { (lightDiagram, buttons, _) -> bfs(lightDiagram.compress(), buttons.compress()) }
    }

    class Vertex(val bytes: UByteArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Vertex

            return bytes.contentEquals(other.bytes)
        }

        override fun hashCode(): Int {
            return bytes.contentHashCode()
        }
    }

    operator fun Vertex.plus(transition: List<Int>): Vertex {
        val newBytes = bytes.copyOf()
        for (index in transition) {
            newBytes[index]++
        }
        return Vertex(newBytes)
    }

    fun bfs(target: Vertex, transitions: List<List<Int>>): Int {
        val start = Vertex(UByteArray(target.bytes.size) { 0u })
        val queue: Queue<Pair<Vertex, Int>> = LinkedList()
        queue.add(start to 0)
        val visited = hashSetOf(start)
        while (queue.isNotEmpty()) {
            val (from, distance) = queue.poll()
            if (from == target) return distance
            for (transition in transitions) {
                val to = from + transition
                if (to !in visited) {
                    visited.add(to)
                    queue.add(to to distance + 1)
                }
            }
        }
        error("Should not reach here")
    }

    fun part2(input: List<Data>): Int {
        return input.sumOf { (_, transitions, joltage) -> bfs(Vertex(joltage), transitions) }
    }
}
