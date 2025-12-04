package day04

class Day04 {
    fun part1(grid: List<CharArray>): Int {
        val n = grid.size
        val m = grid.first().size
        var result = 0
        for (i in 0..<n) {
            for (j in 0..<m) {
                if (grid[i][j] == '@') {
                    var count = 0
                    for (k in (i - 1)..(i + 1)) {
                        for (l in (j - 1)..(j + 1)) {
                            if (k in 0..<n && l in 0..<m && grid[k][l] == '@') {
                                count++
                            }
                        }
                    }
                    if (count <= 4) {
                        result++
                    }
                }
            }
        }
        return result
    }

    fun part2(input: List<CharArray>): Int {
        return input.size * 10
    }
}
