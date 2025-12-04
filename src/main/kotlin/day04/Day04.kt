package day04

class Day04 {
    fun List<CharArray>.countAround(i: Int, j: Int): Int {
        var count = 0
        for (k in (i - 1)..(i + 1)) {
            for (l in (j - 1)..(j + 1)) {
                if (k in 0..<size && l in 0..<first().size && this[k][l] == '@') {
                    count++
                }
            }
        }
        return count
    }

    fun part1(grid: List<CharArray>): Int {
        val n = grid.size
        val m = grid.first().size
        var result = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (grid[i][j] == '@') {
                    if (grid.countAround(i, j) <= 4) {
                        result++
                    }
                }
            }
        }
        return result
    }

    fun part2(grid: List<CharArray>): Int {
        val n = grid.size
        val m = grid.first().size
        var result = 0
        var wasRemoved: Boolean
        do {
            wasRemoved = false
            for (i in 0..<n) {
                for (j in 0..<m) {
                    if (grid[i][j] == '@') {
                        if (grid.countAround(i, j) <= 4) {
                            grid[i][j] = '.'
                            wasRemoved = true
                            result++
                        }
                    }
                }
            }
        } while (wasRemoved)
        return result
    }
}
