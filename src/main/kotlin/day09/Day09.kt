package day09

import kotlin.math.absoluteValue

class Day09 {
    fun part1(points: List<Pair<Long, Long>>): Long {
        val n = points.size
        var result = 0L
        for (i in 0..<(n - 1)) {
            val (x1, y1) = points[i]
            for (j in (i + 1)..<n) {
                val (x2, y2) = points[j]
                val width = (x1 - x2).absoluteValue + 1
                val height = (y1 - y2).absoluteValue + 1
                result = maxOf(result, width * height)
            }
        }
        return result
    }

    data class Point(val x: Long, val y: Long) {
        override fun toString(): String = "($x,$y)"
    }

    fun part2(points: List<Pair<Long, Long>>): Long {
        val polygon = points.map { (x, y) -> Point(x, y) }

        println(polygon)

        return 1L
    }
}
