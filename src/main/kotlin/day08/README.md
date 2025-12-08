# Day 8: Playground

## Intuition

The problem describes connecting junction boxes in 3D space by their closest distances to form circuits—this is precisely the Minimum Spanning Tree (MST) problem. Each junction box is a node, and the edge weight between any two nodes is their Euclidean distance. The key insight is that Kruskal's algorithm, which greedily selects edges in order of increasing weight, naturally solves both parts:

- **Part 1**: After processing a fixed number of connections, we need component sizes
- **Part 2**: We need to find the final edge that connects all components into one

Using a Disjoint Set Union (DSU) data structure allows us to efficiently track which junction boxes belong to the same circuit and detect when a connection actually merges two separate circuits versus connecting already-connected boxes.

## Approach

1. **Edge Generation**: Generate all pairwise edges between the n junction boxes. For efficiency, store squared Euclidean distances (avoiding unnecessary square root computations since we only need relative ordering).

2. **Sort Edges**: Sort all edges by distance. This is the core of Kruskal's algorithm—processing edges in ascending order guarantees we always consider the shortest unprocessed connection.

3. **Part 1 - Fixed Connections**:
    - Initialize DSU with each junction box in its own component
    - Process exactly 1000 union operations (regardless of whether they merge distinct components)
    - Count the size of each resulting component
    - Return the product of the three largest component sizes

4. **Part 2 - Complete Connectivity**:
    - Process edges in sorted order, but only count successful unions (those that actually merge distinct components)
    - A tree with n nodes requires exactly n-1 edges
    - When the (n-1)th successful union occurs, all junction boxes form a single circuit
    - Return the product of X coordinates from the endpoints of this final connecting edge

## Complexity

- Time complexity:
  $$O(n^2 \log n)$$

  Generating all pairwise edges takes $O(n^2)$. Sorting these edges dominates at $O(n^2 \log n^2) = O(n^2 \log n)$. The DSU operations (union/find) run in nearly constant time $O(\alpha(n))$ per operation due to path compression and union by rank, where $\alpha$ is the inverse Ackermann function. Processing up to $O(n^2)$ edges gives $O(n^2 \cdot \alpha(n)) \approx O(n^2)$, which is subsumed by the sorting cost.

- Space complexity:
  $$O(n^2)$$

  Storing all $\binom{n}{2} = O(n^2)$ edges requires quadratic space. The DSU structure requires only $O(n)$ space for parent and rank arrays. The edge list dominates, giving overall $O(n^2)$ space complexity.
