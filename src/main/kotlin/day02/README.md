# Day 2: Gift Shop

## Intuition

The key insight is recognizing that invalid IDs follow a mathematical pattern. For Part 1, numbers like 11, 6464, or 123123 can be expressed as a base repeated twice: `base * multiplier + base`, where the multiplier is $$10^{\text{len(base)}}$$. Instead of checking every number in a range, we can calculate the sum of all such numbers using arithmetic series formulas.

For Part 2, the problem expands to patterns repeated multiple times. While Part 1 has an elegant closed-form solution, Part 2's variability (patterns of any length repeated 2+ times) makes enumeration more practical, but we can still optimize by systematically generating candidates rather than checking every number.

## Approach

### Part 1: Patterns Repeated Exactly Twice

The solution transforms the problem from iteration to direct calculation:

1. **Base Calculation (`String.base()`)**: For a number string, determine the starting base value that generates valid doubled patterns. If the string has odd length, we conceptually extend it to even length. The base is derived from the left half of the string.

2. **Multiplier Calculation (`Long.multiplier()`)**: Calculate $$10^{\text{digits}}$$ for a number, which is used to construct doubled numbers (e.g., for base 64, multiplier is 100, giving 6464 = 64 * 100 + 64).

3. **Range Processing**: For each range:
    - Calculate base values for start and end boundaries
    - Split into two parts to handle potential digit length transitions
    - Use the arithmetic sum formula $$\sum_{i=a}^{b} i = \frac{(a + b)(b - a + 1)}{2}$$ to sum base values
    - Transform to actual doubled numbers: `sum_of_bases * (multiplier + 1)`
    - Handle edge cases where the last generated number exceeds the range

### Part 2: Patterns Repeated At Least Twice

A more comprehensive enumeration approach:

1. **Pattern Length Iteration**: For each possible pattern length (1 to half the maximum number length)

2. **Repetition Count**: For each valid repetition count (minimum 2, maximum based on total digits)

3. **Base Generation**: Starting from the smallest number with the given pattern length ($$10^{\text{partLen}-1}$$), increment and check:
    - Generate the repeated number by string repetition
    - Check if it falls within the current range
    - Use a HashSet to track unique numbers and avoid duplicates across different pattern configurations

4. **Accumulation**: Sum all unique invalid IDs found

## Complexity

### Part 1

- Time complexity: $$O(R)$$
    - Where $$R$$ is the number of ranges
    - Each range is processed with constant-time arithmetic operations
    - No iteration over individual numbers

- Space complexity: $$O(1)$$
    - Only uses a fixed number of variables for calculations
    - No data structures that grow with input size

### Part 2

- Time complexity: $$O(R \cdot L^2 \cdot B)$$
    - Where $$R$$ is the number of ranges
    - $$L$$ is the maximum digit length in the ranges
    - $$B$$ is the average number of base patterns checked per configuration
    - The nested loops over pattern length and repetition count contribute $$L^2$$
    - Base generation adds the $$B$$ factor

- Space complexity: $$O(N)$$
    - Where $$N$$ is the total number of unique invalid IDs found
    - Dominated by the HashSet used to track duplicates
