package day04

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    companion object {
        const val DAY = "04"
        val solution = Day04()
    }

    private fun readInput(fileName: String): List<CharArray> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines().map { it.toCharArray() }
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(13, result)
    }

    @Test
    fun testPart1WithFullData() {
        val input = readInput("Day${DAY}.txt")
        val result = solution.part1(input)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part2(input)
        assertEquals(43, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInput("Day${DAY}.txt")
        val result = solution.part2(input)
        println("result = $result")
    }
}
