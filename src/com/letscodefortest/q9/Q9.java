package com.letscodefortest.q9;

// question : https://leetcode.com/problems/largest-rectangle-in-histogram/

import java.util.Stack;

public class Q9 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 30 ms, faster than 38.69% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 52 MB, less than 18.66% of Java online submissions for Largest Rectangle in Histogram.
     * stack을 이용해 높이가 증가하는 histogram을 만들면서 최대 area를 계산함
     */
    static class Solution1 {
        public int largestRectangleArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxArea = 0;

            for (int i = 0; i < heights.length; i++) {
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                    maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
                }
                stack.push(i);
            }

            while (stack.peek() != -1) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
            }

            return maxArea;
        }
    }

    /**
     * time complexity: O(NlogN) ~ O(N^2)
     * space complexity: O(N)
     * 현재 상태에서 가장 작은 사각형 vs 가운데 기준으로 왼쪽 히스토그램 영역에서 가장 작은 사각형 vs 가운데 기준으로 오른쪽 히스토그램 영역에서 가장 작은
     * 사각형 부분 문제로 나누어 재귀적 탐색
     */
    static class Solution2 {
        public int calc(int[] heights, int start, int end) { // time out
            if (start > end) return 0;
            int minIndex = start;
            for (int i = start; i <= end; i++) if (heights[minIndex] > heights[i]) minIndex = i;
            return Math.max(heights[minIndex] * (end - start + 1), Math.max(calc(heights, start, minIndex - 1),
                                                                            calc(heights, minIndex + 1, end)));

        }

        public int largestRectangleArea(int[] heights) {
            return calc(heights, 0, heights.length - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(s2.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
