# Day 10: Factory

## Intuition

This problem presents two fundamentally different optimization challenges disguised under the same factory machine theme.

**Part 1** is a classic state-space search problem. Since each button toggles specific lights (XOR operation), and we need to transform the initial state (all lights off) to a target state, we're essentially finding the shortest path in a graph where nodes are light configurations and edges are button presses. The key insight is that pressing a button twice cancels out, so we only care about reaching states, not the specific sequence—making BFS ideal for finding the minimum number of transitions.

**Part 2** transforms into an integer linear programming problem. Each button press adds 1 to specific counters, and we need to find non-negative integer press counts that sum to the target joltage values while minimizing total presses. This is solving a system of linear Diophantine equations $Ax = b$ where $x \geq 0$ and we minimize $\sum x_i$.

## Approach

### Part 1: BFS on State Space

1. **State Compression**: Represent light configurations as bitmasks—each light is a bit position, `#` = 1, `.` = 0. Similarly, compress each button's toggle list into a bitmask.

2. **Graph Search**: Starting from state 0 (all lights off), perform BFS where each transition XORs the current state with a button's bitmask. The first time we reach the target state gives us the minimum press count.

3. **Correctness**: BFS guarantees shortest path in an unweighted graph. Since XOR is self-inverse, visiting each state once suffices.

### Part 2: Gaussian Elimination with Free Variable Enumeration

1. **Matrix Formulation**: Construct matrix $A$ where $A[i][j] = 1$ if button $j$ affects counter $i$. The system $Ax = b$ must be solved for non-negative integers $x$.

2. **Gaussian Elimination**: Apply row reduction (over rationals, using GCD to keep integers) to identify pivot columns (dependent variables) and free columns (independent variables).

3. **Enumeration Strategy**: For each assignment of non-negative values to free variables, perform back-substitution to compute pivot variables. If all values are non-negative integers, record the sum.

4. **Pruning**: Early termination when the running sum of free variables already exceeds the best solution found.

5. **Bound on Search**: Free variables are bounded by the sum of target joltage values, since no single button can contribute more than that usefully.

## Complexity

- Time complexity:
    - **Part 1**: $$O(M \cdot 2^L \cdot B)$$ where $M$ = number of machines, $L$ = max lights per machine, $B$ = buttons per machine. The state space has $2^L$ configurations, each with $B$ transitions.
    - **Part 2**: $$O(M \cdot (C \cdot B^2 + V^F \cdot C))$$ where $C$ = counters, $B$ = buttons, $F$ = number of free variables after elimination, and $V$ = max value bound for enumeration. Gaussian elimination is $O(C \cdot B^2)$, and enumeration explores $V^F$ combinations with $O(C)$ back-substitution each.

- Space complexity:
    - **Part 1**: $$O(2^L)$$ for the visited set and BFS queue per machine.
    - **Part 2**: $$O(C \cdot B)$$ for the augmented matrix per machine.
