package com.letscodefortest.q7;

// question : https://leetcode.com/problems/maximal-rectangle/solution/

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.Stack;

public class Q7 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();
    static Solution3 s3 = new Solution3();
    static Solution4 s4 = new Solution4();

    /**
     * time complexity: O(N^2 * M^2); CF) N: the number of row, M: the number of Columns
     * space complexity: O(1)
     * Runtime: 9 ms, faster than 39.98% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 42.1 MB, less than 63.69% of Java online submissions for Maximal Rectangle.
     * optimized brute force
     */
    static class Solution1 {
        public int maximalRectangle(char[][] matrix) {

            int ret = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == '1') {
                        ret = Math.max(ret, check(i, j, matrix));
                    }
                }
            }
            return ret;
        }

        public int check(int i, int j, char[][] matrix) {
            int size = 0;
            int limit_i = matrix.length, limit_j = matrix[0].length;
            for (int k = i; k < limit_i; k++) {
                for (int l = j; l < limit_j; l++) {
                    if (matrix[k][l] == '0') {
                        limit_j = l;
                        if (l == j) limit_i = k;
                        break;
                    }
                    size = Math.max(size, (k - i + 1) * (l - j + 1));
                }
            }

            return size;
        }
    }

    /**
     * time complexity: O(N^2 * M)
     * space complexity: O(N * M)
     * Runtime: 9 ms, faster than 39.98% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 42.1 MB, less than 63.69% of Java online submissions for Maximal Rectangle.
     * 라인별로 사각형의 최대 너비가 될 수 있는 길이를 미리 계산 한 히스토그램을 만들어서 최대 사각형의 넓이 계산
     */
    static class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int maxarea = 0;
            int[][] dp = new int[matrix.length][matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {

                    if (matrix[i][j] == '1') {
                        dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                        int width = dp[i][j];

                        for (int k = i; k >= 0; k--) {
                            width = Math.min(width, dp[k][j]);
                            maxarea = Math.max(maxarea, width * (i - k + 1));
                        }
                    }
                }
            }
            return maxarea;
        }
    }

    /**
     * time complexity: O(N * M)
     * space complexity: O(M)
     * Runtime: 15 ms, faster than 16.27% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 47.8 MB, less than 10.65% of Java online submissions for Maximal Rectangle.
     * 라인 바이 라인으로 각 칼럼의 최대 높이 가능 값을 계산하고 이후 스택을 이용해 maxarea를 계산
     */
    static class Solution3 {

        public int getMaxArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxarea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                }
                stack.push(i);
            }
            while (stack.peek() != -1) {
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
            }
            return maxarea;
        }

        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int maxarea = 0;
            int[] dp = new int[matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
                }
                maxarea = Math.max(maxarea, getMaxArea(dp));
            }

            return maxarea;
        }

    }

    /**
     * time complexity: O(N * M)
     * space complexity: O(M)
     * Runtime: 8 ms, faster than 48.02% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 47.1 MB, less than 14.75% of Java online submissions for Maximal Rectangle.
     * 특정 시작지점으로 부터 시작해 최대 left, right, hight 지점을 한정해 maxarea를 계산하는 방법
     */
    static class Solution4 {
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int m = matrix.length;
            int n = matrix[0].length;

            int[] left = new int[n];
            int[] right = new int[n];
            int[] height = new int[n];

            Arrays.fill(right, n);

            int maxarea = 0;

            for (int i = 0; i < m; i++) {
                int cur_left = 0, cur_right = n;

                for (int j = 0; j < n; j++) {
                    if (matrix[i][j]=='1') height[j]++;
                    else height[j] = 0;
                }

                for (int j = 0; j < n; j++) {
                    if (matrix[i][j]=='1') left[j] = Math.max(left[j], cur_left);
                    else {
                        left[j] = 0;
                        cur_left = j + 1;
                    }
                }

                for (int j = n-1; j >=0 ; j--) {
                    if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                    else {
                        right[j] = n;
                        cur_right = j;
                    }
                }

                for (int j = 0; j < n; j++) {
                    maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
                }
            }

            return maxarea;
        }
    }

    public static void main(String[] args) {
        char[][] matrix =
                {
//                        {'1','0','1','0','0'},
//                        {'1','0','1','1','1'},
//                        {'1','1','1','1','1'},
//                        {'1','0','0','1','0'}

//                        {}

//                        {'0'}

//                        {'1'}

//                        {'0', '0'}

                        {'1', '0'},
                        {'1', '0'}
                };

        System.out.println(s1.maximalRectangle(matrix));
    }
}
