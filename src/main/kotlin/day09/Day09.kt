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

    data class Segment(val p1: Point, val p2: Point) {
        override fun toString(): String = "[$p1;$p2]"
    }

    fun List<Pair<Long, Long>>.toSegments(): List<Segment> {
        val (x1, y1) = this[0]
        val (x2, y2) = this[1]
        val segments = mutableListOf(Segment(Point(x1, y1), Point(x2, y2)))
        val n = size
        for (i in 2..<n) {
            val (x, y) = this[i]
            segments += Segment(segments.last().p2, Point(x, y))
        }
        segments += Segment(segments.last().p2, segments.first().p1)
        return segments
    }

    fun part2(points: List<Pair<Long, Long>>): Long {
        val segments = points.toSegments()

        println(segments)

        return 1L
    }
}
