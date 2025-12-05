package day05

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    companion object {
        const val DAY = "05"
        val solution = Day05()
    }

    private fun readInput(fileName: String): Pair<List<LongRange>, List<Long>> {
        val lines = File("src/test/kotlin/day$DAY/$fileName").readLines()
        val ranges = mutableListOf<LongRange>()
        val nums = mutableListOf<Long>()
        var beforeLineBreak = true
        for (line in lines) {
            if (line.isEmpty()) {
                beforeLineBreak = false
                continue
            }
            when {
                line.isEmpty() -> beforeLineBreak = false
                beforeLineBreak -> line.split("-").map(String::toLong).also { (start, end) -> ranges.add(start..end) }
                else -> nums.add(line.toLong())
            }
        }
        return ranges to nums
    }

    @Test
    fun testPart1WithTestData() {
        val (ranges, nums) = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(ranges, nums)
        assertEquals(3, result)
    }

    @Test
    fun testPart1WithFullData() {
        val (ranges, nums) = readInput("Day${DAY}.txt")
        val result = solution.part1(ranges, nums)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val (ranges, _) = readInput("Day${DAY}_test01.txt")
        val result = solution.part2(ranges)
        assertEquals(14L, result)
    }

    @Test
    fun testPart2WithFullData() {
        val (ranges, _) = readInput("Day${DAY}.txt")
        val result = solution.part2(ranges)
        println("result = $result")
    }
}
