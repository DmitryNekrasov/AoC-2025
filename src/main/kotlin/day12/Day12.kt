package day12

typealias Shape = List<CharArray>

val Shape.area: Int get() = sumOf { line -> line.count { it == '#' } }

fun Shape.rotate(): Shape {
    val rotatedGrid = List(first().size) { CharArray(size) { '.' } }
    for (i in 0..<size) {
        for (j in 0..<first().size) {
            rotatedGrid[j][size - i - 1] = this[i][j]
        }
    }
    return rotatedGrid
}

fun Array<CharArray>.canPlace(rowIndex: Int, colIndex: Int, shape: Shape): Boolean {
    for (i in rowIndex..<(rowIndex + shape.size)) {
        for (j in colIndex..<(colIndex + shape.first().size)) {
            if (shape[i - rowIndex][j - colIndex] == '#') {
                if (this[i][j] == '#') {
                    return false
                }
            }
        }
    }
    return true
}

fun Array<CharArray>.place(rowIndex: Int, colIndex: Int, shape: Shape, char: Char = '#') {
    for (i in rowIndex..<(rowIndex + shape.size)) {
        for (j in colIndex..<(colIndex + shape.first().size)) {
            if (shape[i - rowIndex][j - colIndex] == '#') {
                this[i][j] = char
            }
        }
    }
}

class Region(val n: Int, val m: Int, val quantities: IntArray)

fun Region.canFit(shapes: List<Shape>): Boolean {
    if (n * m < quantities.withIndex().sumOf { (index, count) -> shapes[index].area * count }) return false

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
            for (i in 0..<(n - shape.size + 1)) {
                for (j in 0..<(m - shape.first().size + 1)) {
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
