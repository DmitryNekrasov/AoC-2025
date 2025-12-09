package day09

import kotlin.math.absoluteValue
import kotlin.random.Random

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
            x == x1 && y in minOf(y1, y2)..maxOf(y1, y2)
        else
            y == y1 && x in minOf(x1, x2)..maxOf(x1, x2)
    }

    fun part2(points: List<Pair<Long, Long>>): Long {
        val polygon = points.map { (x, y) -> Point(x, y) }
        var result = 0L
        for (i in 0..<(polygon.lastIndex - 1)) {
            val p1 = polygon[i]
            for (j in (i + 1)..<polygon.lastIndex) {
                val p2 = polygon[j]
                if (testRectangleInPolygon(p1, p2, polygon, 10000)) {
                    val width = (p1.x - p2.x).absoluteValue + 1
                    val height = (p1.y - p2.y).absoluteValue + 1
                    result = maxOf(result, width * height)
                }
            }
        }
        return result
    }

    fun testRectangleInPolygon(p1: Point, p2: Point, polygon: List<Point>, times: Int): Boolean {
        repeat(times) {
            if (!generateRandomPointInRectangle(p1, p2).isInside(polygon)) return false
        }
        return true
    }

    fun generateRandomPointInRectangle(p1: Point, p2: Point): Point = Point(
        random.nextLong(minOf(p1.x, p2.x), maxOf(p1.x, p2.x) + 1),
        random.nextLong(minOf(p1.y, p2.y), maxOf(p1.y, p2.y) + 1)
    )

    companion object {
        val random = Random(0xcafebabe)
    }
}
