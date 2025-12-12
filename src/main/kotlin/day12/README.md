# Day 12: Christmas Tree Farm

## Intuition

This problem is fundamentally a **2D bin packing** or **exact cover** problem: given a rectangular grid and a collection of irregular shapes (polyominoes), determine whether all shapes can be placed without overlap. The key insight is that while this is NP-hard in general, the constrained input sizes (small grids and limited shape counts) make exhaustive backtracking with intelligent pruning tractable.

The shapes resemble pentominoes/hexominoes that can be rotated to fit into the available space. Since we're checking feasibility (not optimization), a depth-first search through placement possibilities suffices.

## Approach

1. **Shape Representation**: Each shape is stored as a 2D character grid where `#` marks occupied cells. This allows direct coordinate mapping when checking placements.

2. **Rotation Pre-computation**: Generate all 4 rotations of each shape upfront using the transformation `rotated[j][height - i - 1] = original[i][j]` (90° clockwise rotation). This avoids redundant computation during backtracking.

3. **Area Pruning**: Before attempting any placements, verify that the total area of all required shapes doesn't exceed the grid area (`n × m`). This quickly rejects impossible cases.

4. **Backtracking Search**: Process shapes by index, placing all required copies of shape `i` before moving to shape `i+1`:
    - For each required copy, try all 4 rotations at all valid grid positions
    - On successful placement, mark cells with `#` and recurse
    - On backtrack, restore cells to `.`
    - Success when all shapes are placed; failure when no valid position exists

5. **Placement Logic**: The `canPlace` function verifies no `#` collision exists; `place` toggles cells between occupied and empty states.

## Complexity

- Time complexity: $$O\left((4 \cdot n \cdot m)^{Q}\right)$$

Where $n \times m$ is the grid size and $Q$ is the total number of shapes to place. Each shape has 4 rotations and $O(nm)$ candidate positions. The exponential nature is mitigated by area pruning and early constraint propagation during placement failures.

- Space complexity: $$O(n \cdot m + k)$$

The grid requires $O(nm)$ space, pre-computed rotations use $O(k)$ space for $k$ shape types (with constant-size shapes), and the recursion stack depth is bounded by the total shape count.
