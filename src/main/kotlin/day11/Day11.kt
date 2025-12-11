package day11

class Day11 {
    fun part1(input: List<Pair<String, List<String>>>): Int {
        input.joinToString("\n").also(::println)
        return input.size
    }

    fun part2(input: List<Pair<String, List<String>>>): Int {
        return input.size * 10
    }
}
