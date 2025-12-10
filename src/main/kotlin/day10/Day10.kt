package day10

private fun String.toIntMask(): Int {
    var result = 0
    for ((i, c) in this.withIndex()) {
        if (c == '#') {
            result = result or (1 shl i)
        }
    }
    return result
}

private fun List<List<Int>>.toIntMask(): List<Int> = map { it.fold(0) { acc, num -> acc or (1 shl num) } }

data class Data(val lightDiagram: String, val buttons: List<List<Int>>, val joltage: List<Int>) {
    override fun toString(): String =
        "[$lightDiagram: ${lightDiagram.toIntMask().toString(2).padStart(16, '0')}]" + buttons.toIntMask()
}

class Day10 {
    fun part1(input: List<Data>): Int {
        input.joinToString("\n").also(::println)

        return input.size
    }

    fun part2(input: List<Data>): Int {
        return input.size * 10
    }
}
