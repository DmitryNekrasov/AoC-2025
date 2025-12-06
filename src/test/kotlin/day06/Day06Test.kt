package day06

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        const val DAY = "06"
        val solution = Day06()
    }

    private fun readInput(fileName: String): Pair<List<List<Long>>, List<Char>> {
        val lines = File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
            .map { it.split(" ").filter(String::isNotBlank) }
        val nums = lines.dropLast(1).map { line -> line.map(String::toLong) }
        val operations = lines.last().map { it.first() }
        return nums to operations
    }

    @Test
    fun testPart1WithTestData() {
        val (nums, operations) = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(nums, operations)
        assertEquals(4277556L, result)
    }

    @Test
    fun testPart1WithFullData() {
        val (nums, operations) = readInput("Day${DAY}.txt")
        val result = solution.part1(nums, operations)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val (nums, operations) = readInput("Day${DAY}_test01.txt")
        val result = solution.part2(nums, operations)
        assertEquals(10, result)
    }

    @Test
    fun testPart2WithFullData() {
        val (nums, operations) = readInput("Day${DAY}.txt")
        val result = solution.part2(nums, operations)
        println("result = $result")
    }
}
