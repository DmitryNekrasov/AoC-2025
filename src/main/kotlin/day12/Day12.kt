package day12

class Shape(val grid: List<CharArray>) {
    val size = grid.sumOf { line -> line.count { it == '#' } }
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
            if (shape.grid[i - rowIndex][j - colIndex] == '#') {
                if (this[i][j] == '#') {
                    return false
                }
            }
        }
    }
    return true
}

fun Array<CharArray>.place(rowIndex: Int, colIndex: Int, shape: Shape, char: Char = '#') {
    for (i in rowIndex..<(rowIndex + shape.grid.size)) {
        for (j in colIndex..<(colIndex + shape.grid.first().size)) {
            if (shape.grid[i - rowIndex][j - colIndex] == '#') {
                this[i][j] = char
            }
        }
    }
}

class Region(val n: Int, val m: Int, val quantities: IntArray)

fun Region.canFit(shapes: List<Shape>): Boolean {
    if (n * m < quantities.withIndex().sumOf { (index, count) -> shapes[index].size * count }) return false

    val grid = Array(n) { CharArray(m) { '.' } }

    val rotatedShapes = shapes.map { shape ->
        val rotations = mutableListOf(shape)
        repeat(3) {
            rotations += rotations.last().rotate()
        }
        rotations
    }

    fun backtrack(shapeIndex: Int): Boolean {
        if (shapeIndex == quantities.size) return true
        if (quantities[shapeIndex] == 0) return backtrack(shapeIndex + 1)
        val rotations = rotatedShapes[shapeIndex]
        for (shape in rotations) {
            for (i in 0..<(n - shape.grid.size + 1)) {
                for (j in 0..<(m - shape.grid.first().size + 1)) {
                    if (grid.canPlace(i, j, shape)) {
                        grid.place(i, j, shape)
                        quantities[shapeIndex]--
                        if (backtrack(shapeIndex)) return true
                        grid.place(i, j, shape, '.')
                        quantities[shapeIndex]++
                    }
                }
            }
        }
        return false
    }

    return backtrack(0)
}

class Day12 {
    fun solve(shapes: List<Shape>, regions: List<Region>): Int = regions.count { region -> region.canFit(shapes) }
}
