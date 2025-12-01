# Day 1: Secret Entrance

## Intuition
The problem involves tracking a circular dial with positions 0-99. Part 1 asks for counting when the dial lands exactly on 0 after each rotation, while Part 2 requires counting every time the dial passes through 0 during the rotation process itself. The key insight is that this is modular arithmetic on a circle, where we can calculate zero-crossings mathematically without simulating each individual click.

## Approach
**Part 1:**
- Maintain the current dial position starting at 50
- For each rotation instruction, calculate the new position using modulo 100 arithmetic
- Left rotations subtract from the position, right rotations add to it
- Apply `.mod(100)` to handle wrap-around (e.g., going left from 5 by 10 clicks lands at 95)
- Check if the final position after each rotation equals 0 and increment the counter

**Part 2:**
- Count how many times we cross position 0 during each rotation, not just at the end
- For right rotations: Calculate crossings as `(current + distance) / 100`
    - This represents how many complete circles (100 positions) we traverse
    - Example: from position 60, rotating right 150 clicks crosses 0 twice (at 100 and 200)
- For left rotations: Apply conditional logic based on starting position:
    - If already at 0: Count `distance / 100` crossings (one crossing per full rotation)
    - If `current ≤ distance`: Cross 0 at least once, then count additional full rotations: `1 + (distance - current) / 100`
    - Otherwise: No crossing occurs (we stay within 0-99 range without wrapping)
- Update the current position after counting crossings

## Complexity
- Time complexity: $$O(n)$$ where n is the number of rotation instructions. Each rotation is processed in constant time with simple arithmetic operations.

- Space complexity: $$O(1)$$ using only a few variables to track the current position and result counter.
