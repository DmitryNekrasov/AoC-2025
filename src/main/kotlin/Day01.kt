class Day01 {
    fun part1(input: List<Pair<Char, Int>>): Int {
        var current = 50
        var result = 0
        for ((direction, num) in input) {
            current = if (direction == 'L') {
                (current - num).mod(100)
            } else {
                (current + num).mod(100)
            }
            if (current == 0) {
                result++
            }
        }
        return result
    }

    fun part2(input: List<Pair<Char, Int>>): Int {
        var current = 50
        var result = 0
        for ((direction, num) in input) {
            if (direction == 'L') {
                result += when {
                    current == 0 -> num / 100
                    current <= num -> 1 + (num - current) / 100
                    else -> 0
                }
                current = (current - num).mod(100)
            } else {
                result += (current + num) / 100
                current = (current + num).mod(100)
            }
        }
        return result
    }
}
