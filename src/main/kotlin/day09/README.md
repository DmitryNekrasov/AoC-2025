# Day 9: Movie Theater

## Intuition
The problem presents two distinct geometric challenges. Part 1 asks for the maximum axis-aligned rectangle area that can be formed using any two red tiles as opposite corners—a straightforward optimization over all point pairs. Part 2 adds a constraint: the red tiles form a polygon (connected by green tiles), and the rectangle must lie entirely within this polygon.

For Part 2, rather than implementing complex computational geometry algorithms for exact polygon-rectangle intersection, a Monte Carlo approach provides an elegant probabilistic solution: if we randomly sample enough points within a candidate rectangle and all lie inside the polygon, we can be confident the entire rectangle is valid.

## Approach

**Part 1:**
1. Iterate through all pairs of red tile coordinates
2. For each pair `(x1, y1)` and `(x2, y2)`, compute the rectangle area as `(|x1 - x2| + 1) × (|y1 - y2| + 1)`
3. Track and return the maximum area found

**Part 2:**
1. Treat the ordered list of red tiles as vertices of a polygon (connected by green tile edges)
2. For each pair of red tiles as potential rectangle corners:
    - Use **Monte Carlo sampling** with 10000 random points uniformly distributed within the rectangle
    - For each sampled point, check if it lies inside the polygon using the **ray casting algorithm**:
        - Cast a horizontal ray from the point to the right
        - Count intersections with vertical polygon edges
        - Odd count means inside, even means outside
    - Points exactly on polygon edges are considered inside
3. If all sampled points pass the containment test, consider the rectangle valid and update the maximum area

The ray casting implementation specifically handles the rectilinear nature of the polygon (only horizontal and vertical edges), simplifying intersection tests to checking vertical edges that cross the ray's y-level.

## Complexity
- Time complexity:
    - Part 1: $$O(n^2)$$ where n is the number of red tiles
    - Part 2: $$O(k \cdot n^3)$$ where k = 10,000 is the sample count. For each of $$O(n^2)$$ pairs, we perform k point-in-polygon tests, each taking $$O(n)$$ time to traverse the polygon edges.

- Space complexity:
    - Part 1: $$O(1)$$ auxiliary space
    - Part 2: $$O(n)$$ for storing the polygon vertex list
