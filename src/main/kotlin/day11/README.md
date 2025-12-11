# Day 11: Reactor

## Intuition

The problem models a network of devices as a directed acyclic graph where we need to count paths. Part 1 asks for all paths from `you` to `out`, while Part 2 requires counting only those paths from `svr` to `out` that traverse both `dac` and `fft` nodes.

Part 1 can be solved with straightforward recursive path counting since the answer is small. However, Part 2's constraint - requiring paths to visit two specific intermediate nodes - demands a more sophisticated approach. The key insight is that we can track multiple "states" of path traversal using dynamic programming: paths that haven't visited either special node, paths that visited `dac`, paths that visited `fft`, and paths that visited both.

## Approach

**Part 1:** Implement a simple DFS that recursively counts all paths. When we reach `out`, we return 1; otherwise, we sum the path counts from all outgoing neighbors. The DAG property guarantees termination without cycle detection.

**Part 2:** Use topological-sort-based dynamic programming with four state channels:
- `TOTAL`: Total paths reaching each node
- `DAC`: Paths that have passed through `dac`
- `FFT`: Paths that have passed through `fft`
- `BOTH`: Paths that have passed through both `dac` and `fft`

First, perform DFS from `svr` to obtain a reverse topological ordering. Then process nodes in topological order, propagating path counts forward through edges. The state transitions are:
- At `dac`: All incoming `TOTAL` paths become `DAC` paths; incoming `FFT` paths become `BOTH` paths
- At `fft`: All incoming `TOTAL` paths become `FFT` paths; incoming `DAC` paths become `BOTH` paths
- At other nodes: States propagate unchanged

The answer is `dp[BOTH][out]` - the count of paths reaching `out` that visited both required nodes.

## Complexity

- Time complexity: $$O(V + E)$$

Both parts traverse each vertex and edge once. Part 1's recursive DFS visits each path, but since the answer (500) is small, the graph structure keeps this bounded. Part 2's topological sort and DP propagation are linear in the graph size.

- Space complexity: $$O(V)$$

Part 1 uses recursion stack space proportional to the longest path. Part 2 maintains the visited array, topological ordering list, and the 4×V DP table, all linear in the number of vertices.
