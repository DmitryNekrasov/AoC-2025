package day11

class Day11 {
    fun part1(graph: List<List<Int>>, you: Int, out: Int): Int {
        fun dfs(from: Int): Int {
            if (from == out) return 1
            var count = 0
            for (to in graph[from]) {
                count += dfs(to)
            }
            return count
        }

        return dfs(you)
    }

    fun part2(graph: List<List<Int>>, svr: Int, out: Int, dac: Int, fft: Int): Long {
        val visited = BooleanArray(graph.size) { false }
        val reversedTopSortOrder = mutableListOf<Int>()

        fun dfs(from: Int) {
            visited[from] = true
            for (to in graph[from]) {
                if (!visited[to]) {
                    dfs(to)
                }
            }
            reversedTopSortOrder.add(from)
        }

        dfs(svr)
        val dp = Array(4) { LongArray(graph.size) { 0L } }
        dp[TOTAL][svr] = 1L
        for (from in reversedTopSortOrder.reversed()) {
            for (to in graph[from]) {
                dp[TOTAL][to] += dp[TOTAL][from]
                when (from) {
                    dac -> {
                        dp[DAC][to] += dp[TOTAL][from]
                        dp[BOTH][to] += dp[FFT][from]
                    }

                    fft -> {
                        dp[FFT][to] += dp[TOTAL][from]
                        dp[BOTH][to] += dp[DAC][from]
                    }

                    else -> {
                        dp[DAC][to] += dp[DAC][from]
                        dp[FFT][to] += dp[FFT][from]
                        dp[BOTH][to] += dp[BOTH][from]
                    }
                }
            }
        }

        return dp[BOTH][out]
    }

    companion object {
        const val TOTAL = 0
        const val DAC = 1
        const val FFT = 2
        const val BOTH = 3
    }
}
