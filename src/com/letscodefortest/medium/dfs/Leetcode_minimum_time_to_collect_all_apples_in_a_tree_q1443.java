package com.letscodefortest.medium.dfs;

import java.util.*;

// https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

public class Leetcode_minimum_time_to_collect_all_apples_in_a_tree_q1443 {
    static Solution1 s1 = new Solution1();

    static class Solution1 {
        List<Set<Integer>> graph;
        Map<Integer, Integer> parentInfo;
        int count;
        boolean[] visited;

        // parent, child 관계가 흐트러지는 input이 들어올 경우 오답
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            graph = new LinkedList<>();
            parentInfo = new HashMap<>();
            count = 0;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                graph.add(new HashSet<>());
            }

            for (int i = 0; i < n - 1; i++) {
                parentInfo.put(edges[i][1], edges[i][0]);
            }

            for (int i = 0; i < edges.length; i++) {
                if (hasApple.get(edges[i][1])) {
                    int child = edges[i][1], parent = edges[i][0];
                    while (true) {
                        graph.get(parent).add(child);
                        graph.get(child).add(parent);
                        child = parent;
                        if (child == 0) {
                            break;
                        } else {
                            parent = parentInfo.get(parent);
                        }
                    }
                }
            }

            dfs(0);
            return count * 2;
        }

        private void dfs(int root) {
            visited[root] = true;
            Set<Integer> child = graph.get(root);
            if (child == null) return;

            for (Integer i : child) {
                if (!visited[i]) {
                    visited[i] = true;
                    count++;
                    dfs(i);
                }
            }
        }
    }

    // https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/solutions/623673/concise-explanation-with-a-picture-for-visualization/
    /**
     * time complexity: O(n + e)
     * space complexity: O(n)
     * Runtime: 176 ms, faster than 11.56% of Java online submissions for Minimum Time to Collect All Apples in a Tree.
     * Memory Usage: 115.2 MB, less than 13.15% of Java online submissions for Minimum Time to Collect All Apples in a Tree.
     * 자식 subtree중 apple이 있는 경우에, 자기 자신 노드의 cost를 재귀를 통해 더해준다는 점이 인상깊었음
     */
    static class Solution2 {
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

            Map<Integer, List<Integer>> graph = createGraph(edges); // to store the graph
            Map<Integer, Boolean> visited = new HashMap<>();

            return dfs(graph, 0, hasApple, 0, visited); // cost of reaching the root is 0. For all others, its 2.

        }

        private int dfs(Map<Integer, List<Integer>> graph, int node, List<Boolean> hasApple, int myCost, Map<Integer, Boolean> visited) {
            Boolean v = visited.getOrDefault(node, false);
            if (v) {
                return 0;
            }
            visited.put(node, true);

            int childrenCost = 0; // cost of traversing all children.

            for (int n : graph.getOrDefault(node, new ArrayList<>())) {
                childrenCost += dfs(graph, n, hasApple, 2, visited); // check recursively for all apples in subtrees.
            }

            if (childrenCost == 0 && hasApple.get(node) == false) {
                // If no child has apples, then we won't traverse the subtree, so cost will be zero.
                // similarly, if current node also does not have the apple, we won't traverse this branch at all, so cost will be zero.
                return 0;
            }

            return childrenCost + myCost;
        }

        private Map<Integer, List<Integer>> createGraph(int[][] edges) {
            Map<Integer, List<Integer>> graph = new HashMap<>();

            for (int i = 0; i < edges.length; i++) {
                List<Integer> list = graph.getOrDefault(edges[i][0], new ArrayList<>()); // Adjacency list representation.
                list.add(edges[i][1]);
                graph.put(edges[i][0], list);

                list = graph.getOrDefault(edges[i][1], new ArrayList<>()); // Adjacency list representation.
                list.add(edges[i][0]);
                graph.put(edges[i][1], list);
            }

            return graph;
        }
    }

    public static void main(String[] args) {
//        System.out.println(s1.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(false, false, true, false, true, true, false)));
//        System.out.println(s1.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(false, false, true, false, false, true, false)));
//        System.out.println(s1.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(false, false, false, false, false, false, false)));
//        System.out.println(s1.minTime(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, List.of(true, true, true, true)));
        System.out.println(s1.minTime(4, new int[][]{{0, 2}, {0, 3}, {2, 1}}, List.of(false, true, false, false)));
    }
}
