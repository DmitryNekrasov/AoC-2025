package day01

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object Companion {
        const val DAY = "01"
        val solution = Day01()
    }

    private fun readInput(fileName: String): List<Pair<Char, Int>> {
        return File("src/test/kotlin/day${DAY}/$fileName")
            .readLines()
            .map { it.first() to it.substring(1).toInt() }
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(3, result)
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
        assertEquals(6, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInput("Day${DAY}.txt")
        val result = solution.part2(input)
        println("result = $result")
    }
}
