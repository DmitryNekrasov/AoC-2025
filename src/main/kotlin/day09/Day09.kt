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

    data class Segment(val p1: Point, val p2: Point)

    fun Segment.intersectsWith(other: Segment): Boolean = when {
        p1.y == p2.y && other.p1.x == other.p2.x -> {
            other.p1.x in 1 + minOf(p1.x, p2.x)..<maxOf(p1.x, p2.x) &&
                    p1.y in 1 + minOf(other.p1.y, other.p2.y)..<maxOf(other.p1.y, other.p2.y)
        }

        p1.x == p2.x && other.p1.y == other.p2.y -> {
            p1.x in 1 + minOf(other.p1.x, other.p2.x)..<maxOf(other.p1.x, other.p2.x) &&
                    other.p1.y in 1 + minOf(p1.y, p2.y)..<maxOf(p1.y, p2.y)
        }

        else -> false
    }

    fun Point.isInside(polygon: List<Point>): Boolean {
        var intersections = 0
        for (i in polygon.indices) {
            val p1 = polygon[i]
            val p2 = polygon[(i + 1) % polygon.size]
            if (this.isOnSegment(p1, p2)) return true
            if (p1.x == p2.x && (p1.y > y) != (p2.y > y) && x < p1.x) {
                intersections++
            }
        }
        return intersections % 2 == 1
    }

    fun Point.isOnSegment(p1: Point, p2: Point): Boolean {
        val (x1, y1) = p1
        val (x2, y2) = p2
        return if (x1 == x2)
            y in minOf(y1, y2)..maxOf(y1, y2)
        else
            x in minOf(x1, x2)..maxOf(x1, x2)
    }

    fun part2(points: List<Pair<Long, Long>>): Long {
        val polygon = points.map { (x, y) -> Point(x, y) }

        println(polygon)

        return 1L
    }
}
