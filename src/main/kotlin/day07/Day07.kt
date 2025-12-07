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

    fun part2(input: List<CharArray>): Long {
        return 1L
    }
}
