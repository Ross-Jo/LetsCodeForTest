package com.letscodefortest.medium;

import java.util.*;

public class Leetcode_q210 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(V+E) 정점의 갯수 + 간선의 갯수
     * space complexity: O(N^2)
     * Runtime: 5 ms, faster than 71.37% of Java online submissions for Course Schedule II.
     * Memory Usage: 40 MB, less than 77.94% of Java online submissions for Course Schedule II.
     * 위상 정렬을 활용한다.
     */
    static class Solution1 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] ret = new int[numCourses];
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, LinkedList<Integer>> map = new HashMap<>();
            int[] inDegree = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                map.put(i, new LinkedList<>());
            }

            for (int[] order : prerequisites) {
                LinkedList<Integer> list = map.get(order[1]);
                list.add(order[0]);
                map.put(order[1], list);
                inDegree[order[0]]++;
            }

            for (int i = 0; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }

            for (int i = 0; i < numCourses; i++) {
                if (queue.isEmpty()) {
                    return new int[0];
                }

                int node = queue.poll();
                ret[i] = node;

                LinkedList<Integer> list = map.get(node);
                for (int e : list) {
                    if (--inDegree[e] == 0) {
                        queue.add(e);
                    }
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(s1.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(s1.findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(s1.findOrder(5, new int[][]{})));
        System.out.println(Arrays.toString(s1.findOrder(2, new int[][]{{0, 1}, {1, 0}})));
        System.out.println(Arrays.toString(s1.findOrder(3, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(s1.findOrder(3, new int[][]{{2, 0}, {2, 1}})));
        System.out.println(Arrays.toString(s1.findOrder(3, new int[][]{{0, 1}, {0, 2}, {1, 2}})));
        System.out.println(Arrays.toString(s1.findOrder(3, new int[][]{{1, 0}, {1, 2}, {0, 1}})));
    }

}
