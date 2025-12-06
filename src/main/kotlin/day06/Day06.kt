package day06

class Day06 {
    fun part1(nums: List<List<Long>>, operations: List<Char>): Long {
        var result = 0L
        for (i in operations.indices) {
            var current = if (operations[i] == '+') 0L else 1L
            for (numList in nums) {
                when (operations[i]) {
                    '*' -> current *= numList[i]
                    else -> current += numList[i]
                }
            }
            result += current
        }
        return result
    }

    fun String.ranges(): List<IntRange> {
        val result = mutableListOf<IntRange>()
        var start = 0
        while (start < length) {
            var i = start + 1
            while (i < length && this[i] == ' ') {
                i++
            }
            result += start..(i - 2)
            start = i
        }
        return result.dropLast(1)
    }

    fun solve(nums: List<String>, operation: Char): Long {
        println(nums)
        println(operation)

        return 1L
    }

    fun part2(input: List<String>): Long {
        input.joinToString("\n").also(::println)
        val numLines = input.dropLast(1)
        val operations = input.last()
        val ranges = operations.ranges().also { println(it) }
        var result = 0L
        for (range in ranges) {
            val numStrings = mutableListOf<String>()
            for (line in numLines) {
                numStrings += line.substring(range)
            }
            result += solve(numStrings, operations[range.first])
        }
        return result
    }
}
