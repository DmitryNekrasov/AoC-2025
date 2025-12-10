package day10

data class Data(val lightDiagram: String, val buttons: List<List<Int>>, val joltage: List<Int>)

class Day10 {
    fun part1(input: List<Data>): Int {
        input.joinToString("\n").also(::println)

        return input.size
    }

    fun part2(input: List<Data>): Int {
        return input.size * 10
    }
}
