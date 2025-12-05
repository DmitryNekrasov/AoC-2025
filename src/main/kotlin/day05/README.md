# Day 5: Cafeteria

## Intuition
Part 1 is straightforward: for each ingredient ID, check if it falls within any of the fresh ingredient ranges. Part 2 is more interesting - instead of checking individual IDs, we need to count all possible IDs covered by the ranges. This is a classic interval merging problem. Overlapping ranges should be combined to avoid double-counting, and the total count is the sum of all unique IDs across merged ranges.

## Approach

### Part 1
For each ingredient ID in the list, check if it falls within any of the fresh ingredient ranges using the `in` operator. Count how many IDs satisfy this condition using `count` with `any` predicate.

### Part 2
This requires merging overlapping intervals and counting total coverage:

1. **Sort ranges** by their start point to enable linear scanning
2. **Initialize** with the first range as the current working range
3. **Iterate** through remaining ranges:
    - If the current range intersects with the next range, merge them using `union`
    - Otherwise, add the current range to the result list and start a new current range
4. **Add** the final current range to the result list
5. **Calculate** the total count by summing the size of each merged range (end - start + 1)

**Key helper functions:**
- `hasIntersectionWith`: Checks if two ranges overlap by testing if either range's start point falls within the other range
- `union`: Merges two ranges by taking the minimum start and maximum end points

## Complexity

### Part 1
- Time complexity: $$O(n \cdot m)$$ where $$n$$ is the number of ingredient IDs and $$m$$ is the number of ranges
- Space complexity: $$O(1)$$ excluding input storage

### Part 2
- Time complexity: $$O(m \log m)$$ where $$m$$ is the number of ranges (dominated by sorting)
- Space complexity: $$O(m)$$ for storing the merged ranges
