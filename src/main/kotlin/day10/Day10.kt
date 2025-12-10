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

    fun UByteArray.push(button: List<Int>, times: UByte) {
        for (index in button) {
            this[index] = (this[index] - times).toUByte()
        }
    }

    fun UByteArray.pull(button: List<Int>, times: UByte) {
        for (index in button) {
            this[index] = (this[index] + times).toUByte()
        }
    }

    fun UByteArray.toKey(): Pair<Long, Short> {
        var low = 0L
        for (i in 0..<minOf(8, size)) {
            low = (low shl 8) or this[i].toLong()
        }
        var high = 0
        for (i in 0..<maxOf(0, size - 8)) {
            high = (high shl 8) or this[i + 8].toInt()
        }
        return low to high.toShort()
    }

    fun solve(buttons: List<List<Int>>, target: UByteArray): Int {
        val cache = hashMapOf<Triple<Int, Long, Short>, Int>()

        fun backtrack(buttonIndex: Int, current: UByteArray): Int {
            if (current.all { it.toInt() == 0 }) return 0
            if (buttonIndex == buttons.size) return INF

            val (low, high) = current.toKey()

            val key = Triple(buttonIndex, low, high)
            cache[key]?.let { return it }

            val button = buttons[buttonIndex]
            val minCount = button.minOf { current[it] }.toInt()

            var result = INF
            for (count in 0..minCount) {
                current.push(button, count.toUByte())
                val sub = backtrack(buttonIndex + 1, current)
                if (sub != INF) {
                    result = minOf(result, sub + count)
                }
                current.pull(button, count.toUByte())
            }

            cache[key] = result
            return result
        }

        return backtrack(0, target)
    }

    fun part2(input: List<Data>): Int {
        return input.sumOf { (_, buttons, joltage) -> solve(buttons, joltage) }
    }

    companion object {
        const val INF = 1_000_000_000
    }
}
