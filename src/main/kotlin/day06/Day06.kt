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

    fun part2(nums: List<List<Long>>, operations: List<Char>): Long {
        return 1L
    }
}
