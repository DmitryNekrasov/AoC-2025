package day03

class Day03 {

    fun String.indexOfMaxInRange(range: IntRange): Int {
        var maxIndex = range.first
        for (i in (maxIndex + 1)..range.last) {
            if (this[i] > this[maxIndex]) {
                maxIndex = i
            }
        }
        return maxIndex
    }

    fun Char.asDigit(): Int = code - '0'.code

    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val i1 = line.indexOfMaxInRange(0..<line.lastIndex)
            val i2 = line.indexOfMaxInRange((i1 + 1)..line.lastIndex)
            result += line[i1].asDigit() * 10 + line[i2].asDigit()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size * 10
    }
}
