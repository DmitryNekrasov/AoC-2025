package day12

data class Shape(val grid: List<CharArray>) {
    override fun toString(): String = grid.joinToString("\n") { String(it) }
}

fun Shape.rotate(): Shape {
    val rotatedGrid = List(grid.first().size) { CharArray(grid.size) { '.' } }
    for (i in 0..<grid.size) {
        for (j in 0..<grid.first().size) {
            rotatedGrid[j][grid.size - i - 1] = grid[i][j]
        }
    }
    return Shape(rotatedGrid)
}

fun Array<CharArray>.canPlace(rowIndex: Int, colIndex: Int, shape: Shape): Boolean {
    for (i in rowIndex..<(rowIndex + shape.grid.size)) {
        for (j in colIndex..<(colIndex + shape.grid.first().size)) {
            if (shape.grid[i - rowIndex][j - colIndex] == '#' && this[i][j] == '#') return false
        }
    }
    return true
}

fun Array<CharArray>.place(rowIndex: Int, colIndex: Int, shape: Shape) {
    for (i in rowIndex..<(rowIndex + shape.grid.size)) {
        for (j in colIndex..<(colIndex + shape.grid.first().size)) {
            if (shape.grid[i - rowIndex][j - colIndex] == '#') {
                this[i][j] = '#'
            }
        }
    }
}

data class Region(val n: Int, val m: Int, val quantities: List<Int>) {
    val grid = Array(n) { CharArray(m) { '.' } }
}

fun Region.canFit(shapes: List<Shape>): Boolean {
    return false
}

class Day12 {
    fun part1(shapes: List<Shape>, regions: List<Region>): Int = regions.count { it.canFit(shapes) }

    fun part2(shapes: List<Shape>, regions: List<Region>): Int {
        return 1
    }
}
