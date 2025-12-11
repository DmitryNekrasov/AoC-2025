package day11

class Day11 {
    class Enumerator<T> {
        private val cache = mutableMapOf<T, Int>()
        operator fun get(x: T): Int = cache.getOrPut(x) { cache.size }
    }

    fun part1(input: List<Pair<String, List<String>>>): Int {
        val n = input.size + 1
        val enumerator = Enumerator<String>()
        val graph = Array(n) { mutableListOf<Int>() }
        for ((fromStr, list) in input) {
            val from = enumerator[fromStr]
            for (toStr in list) {
                graph[from].add(enumerator[toStr])
            }
        }

        val start = enumerator["you"]
        val finish = enumerator["out"]

        fun dfs(from: Int): Int {
            if (from == finish) return 1
            var count = 0
            for (to in graph[from]) {
                count += dfs(to)
            }
            return count
        }

        return dfs(start)
    }

    fun part2(input: List<Pair<String, List<String>>>): Int {
        return input.size * 10
    }
}
