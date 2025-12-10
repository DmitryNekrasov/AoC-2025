package day10

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object Companion {
        const val DAY = "10"
        val solution = Day10()
    }

    private fun String.toLightDiagram(): String = substring(1, lastIndex)

    private fun List<String>.toButtons(): List<List<Int>> =
        map { it.substring(1, it.lastIndex).split(",").map(String::toInt) }

    private fun String.toJoltage(): List<Int> =
        substring(1, lastIndex).split(",").map(String::toInt)

    private fun readInput(fileName: String): List<Data> {
        return File("src/test/kotlin/day$DAY/$fileName")
            .readLines()
            .map { line ->
                line.split(" ").let { tokens ->
                    Data(
                        lightDiagram = tokens.first().toLightDiagram(),
                        buttons = tokens.subList(1, tokens.lastIndex).toButtons(),
                        joltage = tokens.last().toJoltage()
                    )
                }
            }
    }

    @Test
    fun testPart1WithTestData() {
        val input = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(input)
        assertEquals(7, result)
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
