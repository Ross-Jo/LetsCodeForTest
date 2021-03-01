package com.letscodefortest.q9;

// question : https://leetcode.com/problems/largest-rectangle-in-histogram/

import java.util.Stack;

public class Q9 {
    static Solution1 s1 = new Solution1();

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

    public static void main(String[] args) {
        System.out.println(s1.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
