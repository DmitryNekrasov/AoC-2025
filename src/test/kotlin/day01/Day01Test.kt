package day01

import Day01
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

class Day01Test {
    private fun readInput(fileName: String): List<Pair<Char, Int>> {
        return File("src/test/kotlin/day01/$fileName")
            .readLines()
            .map { it.first() to it.substring(1).toInt() }
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day01_test01.txt")
        val result = Day01().part1(input)
        assertEquals(3, result)
    }

    @Test
    fun testPart1WithFullData() {
        val input = readInput("Day01.txt")
        val result = Day01().part1(input)
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val input = readInput("Day01_test01.txt")
        val result = Day01().part2(input)
        assertEquals(6, result)
    }

    @Test
    fun testPart2WithFullData() {
        val input = readInput("Day01.txt")
        val result = Day01().part2(input)
        println("result = $result")
    }
}
