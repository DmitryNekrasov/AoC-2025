# Day 6: Trash Compactor

## Intuition

The problem presents a unique parsing challenge where mathematical expressions are encoded in a columnar format. Part 1 requires straightforward vertical reading of numbers, while Part 2 inverts the perspective entirely—numbers must be reconstructed by reading digits column-by-column from right to left, with the topmost digit being the most significant.

The key insight is recognizing that Part 2 transforms the spatial layout into a digit-assembly problem: each column within a problem contributes one digit position to multiple numbers simultaneously, requiring careful index manipulation to reconstruct the actual values.

## Approach

### Part 1: Direct Column Aggregation

The input is pre-parsed into a matrix of numbers and a list of operations. For each problem (identified by column index):

1. Initialize an accumulator based on the operation (0 for addition, 1 for multiplication)
2. Iterate through all rows, applying the operation to aggregate values from that column
3. Sum all problem results into the grand total

### Part 2: Right-to-Left Column Reconstruction

This part requires working with raw string input to preserve spatial positioning:

1. **Range Detection** (`ranges()`): Scan the operation line to identify problem boundaries. Problems are separated by space-only columns. Track ranges by finding transitions from operators to the next non-space character.

2. **Number Reconstruction** (`solve()`): For each problem's substring:
    - Iterate columns from right to left (rightmost column = units place, moving left increases place value)
    - For each column position, read digits top-to-bottom to form a number (top = most significant)
    - Build numbers using the standard positional notation: `current = current * 10 + digit`
    - Apply the operation across all reconstructed numbers

3. **Aggregation**: Sum results from all problems processed via their identified ranges.

The elegant aspect of the reconstruction is that iterating columns right-to-left naturally handles the place-value semantics—each column processed represents the next higher power of 10 for all numbers in that problem.

## Complexity

- Time complexity:
    - Part 1: $$O(n \cdot m)$$ where $n$ is the number of rows (operands per problem) and $m$ is the number of problems
    - Part 2: $$O(n \cdot w)$$ where $w$ is the total width of the input, since we process each character position across all rows

- Space complexity:
    - Part 1: $$O(1)$$ additional space beyond input storage
    - Part 2: $$O(w)$$ for storing column ranges and extracted substrings during processing
