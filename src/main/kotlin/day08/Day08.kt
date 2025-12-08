package day08

import kodvent.datastructures.DisjointSetUnion
import kodvent.increment

class Day08 {
    class Enumerator<T> {
        private val cache = mutableMapOf<T, Int>()
        operator fun get(x: T) = cache.getOrPut(x) { cache.size }
    }

    val Long.sqr: Long get() = this * this

    fun distance(x1: Long, x2: Long, y1: Long, y2: Long, z1: Long, z2: Long): Long =
        (x2 - x1).sqr + (y2 - y1).sqr + (z2 - z1).sqr

    fun getSortedEdges(points: List<List<Long>>): List<Triple<Int, Int, Long>> {
        val edges = mutableListOf<Triple<Int, Int, Long>>()
        val enumerator = Enumerator<List<Long>>()
        val n = points.size
        for (i in 0..<(n - 1)) {
            val (x1, y1, z1) = points[i]
            val from = enumerator[points[i]]
            for (j in (i + 1)..<n) {
                val (x2, y2, z2) = points[j]
                val to = enumerator[points[j]]
                val distance = distance(x1, x2, y1, y2, z1, z2)
                edges += Triple(from, to, distance)
            }
        }
        return edges.sortedBy { it.third }
    }

    fun part1(points: List<List<Long>>, connectionNumber: Int): Int {
        val sortedEdges = getSortedEdges(points)
        val dsu = DisjointSetUnion(points.size)
        repeat(connectionNumber) { dsu.union(sortedEdges[it].first, sortedEdges[it].second) }

        val count = HashMap<Int, Int>()
        for (i in points.indices) {
            val root = dsu.find(i)
            count.increment(root)
        }

        val (a, b, c) = count.values.sortedDescending().take(3)
        return a * b * c
    }

    fun part2(points: List<List<Long>>): Long {
        val sortedEdges = getSortedEdges(points)
        val dsu = DisjointSetUnion(points.size)
        var edgeNumber = 0
        for ((from, to, distance) in sortedEdges) {
            if (dsu.union(from, to)) {
                edgeNumber++
            }
            if (edgeNumber == points.size - 1) {
                return points[from][0] * points[to][0]
            }
        }
        error("Should not reach here")
    }
}
