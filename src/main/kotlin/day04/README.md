# Day 4: Printing Department

## Intuition
The problem asks us to identify and remove paper rolls that can be accessed by forklifts based on their neighbor count. A roll is accessible if it has fewer than 4 adjacent rolls (out of 8 possible neighbors). The key insight is that we need to count the roll itself plus its neighbors, so a roll with ≤ 3 neighbors is accessible.

For Part 2, this becomes a cascading removal problem - as we remove accessible rolls, previously inaccessible rolls may become accessible. This suggests an iterative simulation approach where we repeatedly scan and remove rolls until the grid stabilizes.

## Approach
The solution uses a helper function `countAround(i, j)` that counts all '@' symbols in the 3×3 grid centered at position (i, j), including the center cell itself. This gives us the total number of rolls in the immediate vicinity.

**Part 1:** Perform a single pass through the grid. For each paper roll ('@'), check if `countAround(i, j) ≤ 4`. Since this count includes the roll itself, this condition means the roll has at most 3 neighbors, making it accessible. Count all such rolls.

**Part 2:** Use an iterative simulation with a do-while loop:
1. Scan the entire grid for accessible rolls (those with `countAround ≤ 4`)
2. Mark all accessible rolls as removed ('.' character) in the same iteration
3. Track whether any rolls were removed
4. Repeat until no rolls are removed in a complete iteration

This approach ensures that rolls are removed in waves, and each wave may expose new rolls for removal in subsequent iterations.

## Complexity
- Time complexity:
    - Part 1: $$O(n \times m)$$ where $$n$$ and $$m$$ are the grid dimensions. We make a single pass through the grid, and `countAround` is $$O(1)$$.
    - Part 2: $$O(k \times n \times m)$$ where $$k$$ is the number of removal iterations. In the worst case, $$k$$ could be $$O(n \times m)$$ if only one roll is removed per iteration, resulting in $$O((n \times m)^2)$$ overall complexity.

- Space complexity: $$O(1)$$
    - Both parts use only a constant amount of extra space beyond the input grid. Part 2 modifies the input grid in place rather than creating copies.
