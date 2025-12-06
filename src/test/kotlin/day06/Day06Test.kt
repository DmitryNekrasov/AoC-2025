package day06

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        const val DAY = "06"
        val solution = Day06()
    }

    private fun readInputPart1(fileName: String): Pair<List<List<Long>>, List<Char>> {
        val lines = File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
            .map { it.split(" ").filter(String::isNotBlank) }
        val nums = lines.dropLast(1).map { line -> line.map(String::toLong) }
        val operations = lines.last().map { it.first() }
        return nums to operations
    }

    private fun readInputPart2(fileName: String): List<String> {
        return File("src/test/kotlin/day$DAY/$fileName").readLines()
    }

    @Test
    fun testPart1WithTestData() {
        val (nums, operations) = readInputPart1("Day${DAY}_test01.txt")
        val result = solution.part1(nums, operations)
        assertEquals(4277556L, result)
    }

    @Test
    fun testPart1WithFullData() {
        val (nums, operations) = readInputPart1("Day${DAY}.txt")
        val result = solution.part1(nums, operations)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val input = readInputPart2("Day${DAY}_test01.txt")
        val result = solution.part2(input)
        assertEquals(3263827L, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInputPart2("Day${DAY}.txt")
        val result = solution.part2(input)
        println("result = $result")
    }
}
