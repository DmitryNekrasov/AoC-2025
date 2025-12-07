package day07

class Day07 {
    fun part1(grid: List<CharArray>): Int {
        val n = grid.size
        val m = grid.first().size
        var result = 0
        for (i in 1..<n) {
            for (j in 0..<m) {
                if (grid[i - 1][j] == 'S' || grid[i - 1][j] == '|') {
                    if (grid[i][j] == '.') {
                        grid[i][j] = '|'
                    } else if (grid[i][j] == '^') {
                        grid[i][j - 1] = '|'
                        grid[i][j + 1] = '|'
                        result++
                    }
                }
            }
        }
        return result
    }

    fun part2(grid: List<CharArray>): Long {
        val n = grid.size
        val m = grid.first().size
        val dp = Array(n) { LongArray(m) }
        dp[0][grid.first().indexOf('S')] = 1L
        for (i in 1..<n) {
            for (j in 0..<m) {
                if (grid[i - 1][j] == 'S' || grid[i - 1][j] == '|') {
                    if (grid[i][j] == '.' || grid[i][j] == '|') {
                        grid[i][j] = '|'
                        dp[i][j] += dp[i - 1][j]
                    } else if (grid[i][j] == '^') {
                        grid[i][j - 1] = '|'
                        grid[i][j + 1] = '|'
                        dp[i][j - 1] += dp[i - 1][j]
                        dp[i][j + 1] += dp[i - 1][j]
                    }
                }
            }
        }
        return dp[n - 1].sum()
    }
}
