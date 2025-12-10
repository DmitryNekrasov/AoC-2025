package day10

import java.util.LinkedList
import java.util.Queue

private fun String.toIntMask(): Int {
    var result = 0
    for ((i, c) in this.withIndex()) {
        if (c == '#') {
            result = result or (1 shl i)
        }
    }
    return result
}

private fun List<List<Int>>.toIntMask(): List<Int> = map { it.fold(0) { acc, num -> acc or (1 shl num) } }

data class Data(val lightDiagram: String, val buttons: List<List<Int>>, val joltage: List<Int>) {
    override fun toString(): String =
        "[$lightDiagram: ${lightDiagram.toIntMask().toString(2).padStart(16, '0')}]" + buttons.toIntMask()
}

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

class Day10 {
    fun part1(input: List<Data>): Int {
        return input.sumOf { (lightDiagram, buttons, _) -> bfs(lightDiagram.toIntMask(), buttons.toIntMask()) }
    }

    fun part2(input: List<Data>): Int {
        return input.size * 10
    }
}
