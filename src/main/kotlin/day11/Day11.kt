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
        graph.withIndex().joinToString("\n") { (index, list) -> "$index: ${list.toList().toString()}" }.also(::println)
        println("svr: $svr, out: $out, dac: $dac, fft: $fft")

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
        val topSortOrder = reversedTopSortOrder.reversed()
        val dp = Array(4) { LongArray(graph.size) { 0L } }
        dp[TOTAL][svr] = 1L
        for (v in topSortOrder) {
            for (u in graph[v]) {
                dp[TOTAL][u] += dp[TOTAL][v]
                if (v != dac && v != fft) {
                    dp[DAC][u] += dp[DAC][v]
                    dp[FFT][u] += dp[FFT][v]
                    dp[BOTH][u] += dp[BOTH][v]
                } else {
                    if (v == dac) {
                        dp[DAC][u] += dp[TOTAL][v]
                        dp[BOTH][u] += dp[FFT][v]
                    } else {
                        dp[FFT][u] += dp[TOTAL][v]
                        dp[BOTH][u] += dp[DAC][v]
                    }
                }
            }
        }

        dp.joinToString("\n") { it.toList().toString() }.also(::println)

        return dp[BOTH][out]
    }

    companion object {
        const val TOTAL = 0
        const val DAC = 1
        const val FFT = 2
        const val BOTH = 3
    }
}
