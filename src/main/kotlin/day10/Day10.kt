package day10

import kodvent.math.gcd
import java.util.LinkedList
import java.util.Queue

data class Data(val lightDiagram: String, val buttons: List<List<Int>>, val joltage: List<Int>)

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

    fun solve(mat: Array<IntArray>, vec: List<Int>): Int {
        val m = mat.size
        val n = mat.first().size
        val aug = Array(m) { i -> IntArray(n + 1) { j -> if (j < n) mat[i][j] else vec[i] } }
        val pivotCols = mutableListOf<Int>()
        var pivotRow = 0

        for (col in 0..<n) {
            val found = (pivotRow..<m).firstOrNull { aug[it][col] != 0 } ?: continue
            aug[pivotRow] = aug[found].also { aug[found] = aug[pivotRow] }
            pivotCols.add(col)
            for (row in pivotRow + 1 until m) {
                if (aug[row][col] == 0) continue
                val g = gcd(aug[pivotRow][col], aug[row][col])
                val mulPivot = aug[row][col] / g
                val mulRow = aug[pivotRow][col] / g
                for (j in 0..n) {
                    aug[row][j] = aug[row][j] * mulRow - aug[pivotRow][j] * mulPivot
                }
            }
            pivotRow++
        }

        val rank = pivotCols.size
        val pivotColSet = pivotCols.toSet()
        val freeVars = (0..<n).filter { it !in pivotColSet }
        val x = IntArray(n)
        var result = Int.MAX_VALUE

        fun backSubstitute(): Boolean {
            for (r in rank - 1 downTo 0) {
                val col = pivotCols[r]
                var rhs = aug[r][n]
                for (j in col + 1..<n) {
                    rhs -= aug[r][j] * x[j]
                }
                if (rhs % aug[r][col] != 0) return false
                x[col] = rhs / aug[r][col]
                if (x[col] < 0) return false
            }
            return true
        }

        fun search(freeIdx: Int, sumFree: Long) {
            if (sumFree >= result) return
            if (freeIdx == freeVars.size) {
                if (backSubstitute()) {
                    val total = x.sum()
                    if (total < result) result = total
                }
                return
            }
            val col = freeVars[freeIdx]
            val maxVal = vec.sum().coerceAtLeast(1)
            for (v in 0..maxVal) {
                x[col] = v
                search(freeIdx + 1, sumFree + v)
            }
        }

        search(0, 0)
        return result
    }

    fun part2(input: List<Data>): Int {
        var result = 0
        for ((_, buttons, joltage) in input) {
            val mat = Array(joltage.size) { IntArray(buttons.size) }
            for ((buttonIndex, button) in buttons.withIndex()) {
                for (index in button) {
                    mat[index][buttonIndex] = 1
                }
            }
            result += solve(mat, joltage)
        }
        return result
    }
}
