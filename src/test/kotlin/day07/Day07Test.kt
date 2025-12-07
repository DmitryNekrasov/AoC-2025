package day07

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    companion object Companion {
        const val DAY = "07"
        val solution = Day07()
    }

    private fun readInput(fileName: String): List<CharArray> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines().map(String::toCharArray)
    }

    @Test
    fun testPart1WithTestData() {
        val grid = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(grid)
        assertEquals(21, result)
    }

    @Test
    fun testPart1WithFullData() {
        val grid = readInput("Day${DAY}.txt")
        val result = solution.part1(grid)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val grid = readInput("Day${DAY}_test01.txt")
        val result = solution.part2(grid)
        assertEquals(10, result)
    }

    @Test
    fun testPart2WithFullData() {
        val grid = readInput("Day${DAY}.txt")
        val result = solution.part2(grid)
        println("result = $result")
    }
}
