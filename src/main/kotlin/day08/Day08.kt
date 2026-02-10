package day08

import kodvent.datastructures.DisjointSetUnion
import kodvent.collections.increment

class Day08 {
    val Long.sqr: Long get() = this * this

    fun distance(x1: Long, x2: Long, y1: Long, y2: Long, z1: Long, z2: Long): Long =
        (x2 - x1).sqr + (y2 - y1).sqr + (z2 - z1).sqr

    fun getSortedEdges(points: List<List<Long>>): List<Triple<Int, Int, Long>> {
        val edges = mutableListOf<Triple<Int, Int, Long>>()
        val n = points.size
        for (i in 0..<(n - 1)) {
            val (x1, y1, z1) = points[i]
            for (j in (i + 1)..<n) {
                val (x2, y2, z2) = points[j]
                edges += Triple(i, j, distance(x1, x2, y1, y2, z1, z2))
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
            count.increment(dsu.find(i))
        }
        return count.values.sortedDescending().take(3).reduce(Int::times)
    }

    fun part2(points: List<List<Long>>): Long {
        val dsu = DisjointSetUnion(points.size)
        var edgeNumber = 0
        for ((from, to, _) in getSortedEdges(points)) {
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
