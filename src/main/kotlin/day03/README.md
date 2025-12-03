# Day 3: Lobby

## Intuition
The problem asks us to select specific batteries (digits) from each bank to maximize the resulting joltage (number). The key insight is that to maximize a number, we need to greedily select the largest available digits for the most significant positions first. This is a classic greedy algorithm problem where local optimal choices lead to a global optimal solution.

For Part 1, we need exactly 2 digits, so we find the largest digit first, then find the largest digit from the remaining positions.

For Part 2, we need exactly 12 digits, so we extend the greedy approach by iteratively selecting the largest available digit for each position from most significant to least significant, ensuring we always leave enough digits for the remaining positions.

## Approach
**Part 1 (2 batteries):**
1. For each line, find the maximum digit in the range from start to second-to-last position
2. Then find the maximum digit in the remaining range (from the position after the first selected digit to the end)
3. Combine these two digits: `firstDigit * 10 + secondDigit`
4. Sum all banks' maximum joltages

**Part 2 (12 batteries):**
1. For each line, iterate through positions from most significant (power of 10^11) to least significant (power of 10^0)
2. For each position k (counting down from 11 to 0):
    - Find the maximum digit in the valid range: `(currentIndex + 1) to (line.lastIndex - k)`
    - This range ensures we start after the previously selected position and leave exactly k digits remaining for future selections
    - Add the contribution: `digit * 10^k` to the result
    - Update currentIndex to the position of the selected digit
3. Sum all banks' maximum joltages

The range calculation `(currentIndex + 1)..(line.lastIndex - k)` is crucial: it guarantees we can always select the remaining k digits after making the current choice, while maximizing the current position's value.

## Complexity
- Time complexity: $$O(n \cdot m)$$ where n is the average length of each line and m is the number of lines. For each line, Part 1 performs two linear scans ($$O(n)$$), and Part 2 performs 12 linear scans which is still $$O(n)$$ since 12 is a constant.

- Space complexity: $$O(1)$$ auxiliary space. The solution only uses a constant amount of extra space for storing indices and intermediate results, not counting the input storage.
