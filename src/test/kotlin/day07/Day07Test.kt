package day07

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    companion object Companion {
        const val DAY = "07"
        val solution = Day07()
    }

    private fun readInput(fileName: String): List<String> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(1, result)
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
        assertEquals(10, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInput("Day${DAY}.txt")
        val result = solution.part2(input)
        println("result = $result")
    }
}
