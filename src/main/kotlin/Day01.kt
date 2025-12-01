class Day01 {
    fun part1(input: List<Pair<Char, Int>>): Int {
        var current = 50
        var result = 0
        for ((direction, num) in input) {
            current = if (direction == 'L') {
                (current - num).mod(MOD)
            } else {
                (current + num).mod(MOD)
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
                    current == 0 -> num / MOD
                    current <= num -> 1 + (num - current) / MOD
                    else -> 0
                }
                current = (current - num).mod(MOD)
            } else {
                result += (current + num) / MOD
                current = (current + num).mod(MOD)
            }
        }
        return result
    }

    companion object {
        const val MOD = 100
    }
}
