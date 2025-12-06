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

    fun solve(numsString: List<String>, operation: Char): Long {
        val numLen = numsString.first().length
        val nums = mutableListOf<Long>()
        for (i in (numLen - 1) downTo 0) {
            var current = 0L
            val d = 10
            for (num in numsString) {
                if (num[i].isDigit()) {
                    current *= d
                    current += num[i].digitToInt()
                }
            }
            nums += current
        }
        var result = if (operation == '+') 0L else 1L
        for (num in nums) {
            when (operation) {
                '+' -> result += num
                else -> result *= num
            }
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val numLines = input.dropLast(1)
        val operationsLine = input.last()
        val ranges = operationsLine.ranges()
        var result = 0L
        for (range in ranges) {
            val numStrings = mutableListOf<String>()
            for (line in numLines) {
                numStrings += line.substring(range)
            }
            result += solve(numStrings, operationsLine[range.first])
        }
        return result
    }
}
