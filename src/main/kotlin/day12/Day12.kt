package day12

data class Shape(val grid: List<CharArray>) {
    override fun toString(): String = grid.joinToString("\n") { String(it) }
}

data class Region(val n: Int, val m: Int, val quantities: List<Int>)

class Day12 {
    fun part1(shapes: List<Shape>, regions: List<Region>): Int {
        shapes.joinToString("\n") { "$it\n" }.also(::println)
        regions.joinToString("\n").also(::println)

        return 0
    }

    fun part2(shapes: List<Shape>, regions: List<Region>): Int {
        return 1
    }
}
