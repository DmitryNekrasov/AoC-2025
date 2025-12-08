package day08

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    companion object Companion {
        const val DAY = "08"
        val solution = Day08()
    }

    private fun readInput(fileName: String): List<List<Long>> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines().map { it.split(",").map(String::toLong) }
    }

    @Test
    fun testPart1WithTestData() {
        val points = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(points)
        assertEquals(40L, result)
    }

    @Test
    fun testPart1WithFullData() {
        val points = readInput("Day${DAY}.txt")
        val result = solution.part1(points)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val points = readInput("Day${DAY}_test01.txt")
        val result = solution.part2(points)
        assertEquals(10, result)
    }

    @Test
    fun testPart2WithFullData() {
        val points = readInput("Day${DAY}.txt")
        val result = solution.part2(points)
        println("result = $result")
    }
}
