package day12

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object Companion {
        const val DAY = "12"
        val solution = Day12()
    }

    private fun readInput(fileName: String): Pair<List<Shape>, List<Region>> {
        val lines = File("src/test/kotlin/day$DAY/$fileName").readLines()
        val shapes = mutableListOf<Shape>()
        for (i in 1..26 step 5) {
            val grid = mutableListOf<CharArray>()
            for (j in i..<(i + 3)) {
                grid += lines[j].toCharArray()
            }
            shapes += grid
        }
        val regions = (30..lines.lastIndex).map { i ->
            lines[i].split(": ").let { (sizeStr, quantitiesStr) ->
                val (n, m) = sizeStr.split("x").map(String::toInt)
                Region(n, m, quantitiesStr.split(" ").map(String::toInt).toIntArray())
            }
        }
        return shapes to regions
    }

    @Test
    fun testWithTestData() {
        val (shapes, regions) = readInput("Day${DAY}_test01.txt")
        val result = solution.solve(shapes, regions)
        assertEquals(2, result)
    }

    @Test
    fun testWithFullData() {
        val (shapes, regions) = readInput("Day${DAY}.txt")
        val result = solution.solve(shapes, regions)
        println("result = $result")
    }
}
