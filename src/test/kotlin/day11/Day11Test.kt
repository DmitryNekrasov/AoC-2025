package day11

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object Companion {
        const val DAY = "11"
        val solution = Day11()
    }

    class Enumerator<T> {
        private val cache = mutableMapOf<T, Int>()
        operator fun get(x: T): Int = cache.getOrPut(x) { cache.size }
    }

    private fun readInput(fileName: String): Pair<List<List<Int>>, Enumerator<String>> {
        val input = File("src/test/kotlin/day$DAY/$fileName")
            .readLines().map { line -> line.split(": ").let { (key, value) -> key to value.split(" ") } }
        val enumerator = Enumerator<String>()
        val graph = List(input.size + 1) { mutableListOf<Int>() }
        for ((fromStr, list) in input) {
            val from = enumerator[fromStr]
            for (toStr in list) {
                graph[from].add(enumerator[toStr])
            }
        }
        return graph to enumerator
    }

    @Test
    fun testPart1WithTestData() {
        val (graph, enumerator) = readInput("Day${DAY}_test01.txt")
        val result = solution.part1(graph, enumerator["you"], enumerator["out"])
        assertEquals(5, result)
    }

    @Test
    fun testPart1WithFullData() {
        val (graph, enumerator) = readInput("Day${DAY}.txt")
        val result = solution.part1(graph, enumerator["you"], enumerator["out"])
        println("result = $result")
    }

    @Test
    fun testPart2WithTestData() {
        val (graph, enumerator) = readInput("Day${DAY}_test02.txt")
        val result = solution.part2(graph, enumerator["you"], enumerator["out"], enumerator["dac"], enumerator["fft"])
        assertEquals(2, result)
    }

    @Test
    fun testPart2WithFullData() {
        val (graph, enumerator) = readInput("Day${DAY}.txt")
        val result = solution.part2(graph, enumerator["you"], enumerator["out"], enumerator["dac"], enumerator["fft"])
        println("result = $result")
    }
}
