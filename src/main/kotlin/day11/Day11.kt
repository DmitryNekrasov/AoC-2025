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

    fun part2(graph: List<List<Int>>, svr: Int, out: Int, dac: Int, fft: Int): Int {
        fun dfs(from: Int, state: Int): Int {
            if (from == out) return if ((state and DAC) != 0 && (state and FFT) != 0) 1 else 0
            var count = 0
            for (to in graph[from]) {
                count += dfs(
                    to, when (to) {
                        dac -> state or DAC
                        fft -> state or FFT
                        else -> state
                    }
                )
            }
            return count
        }

        return dfs(svr, 0)
    }

    companion object {
        const val DAC = 0b01
        const val FFT = 0b10
    }
}
