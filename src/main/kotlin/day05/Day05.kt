package day05

class Day05 {
    fun part1(ranges: List<LongRange>, nums: List<Long>): Int =
        nums.count { num -> ranges.any { range -> num in range } }

    infix fun LongRange.hasIntersectionWith(other: LongRange): Boolean = first in other || other.first in this

    infix fun LongRange.union(other: LongRange): LongRange = minOf(first, other.first)..maxOf(last, other.last)

    fun part2(ranges: List<LongRange>): Long {
        val sortedRanges = ranges.sortedBy { it.first }
        var current = sortedRanges.first()
        val unitedRanges = mutableListOf<LongRange>()
        for (range in sortedRanges.drop(1)) {
            if (current hasIntersectionWith range) {
                current = current union range
            } else {
                unitedRanges += current
                current = range
            }
        }
        unitedRanges += current
        return unitedRanges.sumOf { it.last - it.first + 1 }
    }
}
