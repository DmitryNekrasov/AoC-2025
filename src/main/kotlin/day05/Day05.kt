package day05

class Day05 {
    fun part1(ranges: List<LongRange>, nums: List<Long>): Int =
        nums.count { num -> ranges.any { range -> num in range } }

    fun part2(ranges: List<LongRange>, nums: List<Long>): Int {
        return ranges.size * 10
    }
}
