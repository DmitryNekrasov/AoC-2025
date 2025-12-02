package day02

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    companion object Companion {
        const val DAY = "02"
        val solution = Day02()
    }


    private fun readInput(fileName: String): List<Pair<Long, Long>> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
            .first()
            .split(",")
            .map { interval ->
                interval
                    .split("-")
                    .map(String::toLong)
                    .let { (start, end) -> start to end }
            }
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(1227775554L, result)
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
