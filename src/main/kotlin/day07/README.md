# Day 7: Laboratories

## Intuition

The problem describes a tachyon beam simulation through a manifold grid. A beam starts at position `S`, travels downward, and splits into two beams (left and right) whenever it encounters a splitter (`^`).

For Part 1, we simply need to count how many splitters are actually hit by beams—essentially counting split events.

For Part 2, the paradigm shifts to counting distinct timelines. Each time a particle hits a splitter, time itself splits into two timelines—one where the particle went left, one where it went right. This naturally maps to a dynamic programming problem where we track how many distinct paths (timelines) reach each cell.

## Approach

### Part 1: Beam Simulation

Process the grid row-by-row from top to bottom. For each cell:

1. Check if the cell directly above contains a beam (`S` or `|`)
2. If the current cell is empty (`.`), mark it as containing a beam (`|`)
3. If the current cell is a splitter (`^`), mark both adjacent cells (left and right) as beams and increment the split counter

The in-place modification ensures that beams propagate correctly as we scan downward, and merged beams (multiple beams hitting the same empty space) are handled naturally since marking `|` multiple times has no additional effect.

### Part 2: Timeline Counting via DP

Define `dp[i][j]` as the number of distinct timelines that have a particle at position `(i, j)`.

**Base case:** `dp[0][start_col] = 1` where `start_col` is the column containing `S`.

**Transition:** For each cell `(i, j)` where a beam arrives from above:

- If the current cell is empty (`.` or already `|`): the particle continues straight down, so `dp[i][j] += dp[i-1][j]`
- If the current cell is a splitter (`^`): the timeline splits, so both `dp[i][j-1] += dp[i-1][j]` and `dp[i][j+1] += dp[i-1][j]`

**Result:** Sum all values in the last row of the DP table—this counts all timelines that successfully traversed the manifold.

The key insight is that timeline counts are additive: if 5 timelines reach a splitter, each one independently branches into 2, contributing 5 timelines to the left path and 5 to the right path.

## Complexity

- Time complexity:
  $$O(n \times m)$$

Both parts iterate through every cell in the grid exactly once, where $n$ is the number of rows and $m$ is the number of columns.

- Space complexity:
    - Part 1: $$O(1)$$ auxiliary space (grid is modified in-place)
    - Part 2: $$O(n \times m)$$ for the DP table storing timeline counts at each cell
