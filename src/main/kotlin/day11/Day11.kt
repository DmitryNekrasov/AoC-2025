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

    fun part2(graph: List<List<Int>>, you: Int, out: Int, dac: Int, fft: Int): Int {
        return 0
    }
}
