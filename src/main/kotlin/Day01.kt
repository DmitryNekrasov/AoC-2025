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
        return input.size
    }
}
