package day03

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        const val DAY = "03"
        val solution = Day03()
    }


    private fun readInput(fileName: String): List<String> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(357, result)
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
        assertEquals(3121910778619L, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInput("Day${DAY}.txt")
        val result = solution.part2(input)
        println("result = $result")
    }
}
