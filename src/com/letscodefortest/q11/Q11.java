package com.letscodefortest.q11;

// question : https://leetcode.com/problems/cherry-pickup/

import java.util.Arrays;

public class Q11 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N^3)
     * space complexity: O(N^3)
     * Runtime: 12 ms, faster than 89.45% of Java online submissions for Cherry Pickup.
     * Memory Usage: 39.3 MB, less than 56.21% of Java online submissions for Cherry Pickup.
     * 2명의 인물이 마지막 지점까지 cherry를 pick하기 위해 걸어가서 최대한 많이 체리를 줍는 경우와 동일하므로 이와 같은 상황을 설정한 후
     * dp를 이용해 문제 상황을 해결해나간다. 이때 (r1, c1), (r2, c2)는 움직인 걸음수가 동일하기에 r1 + c1 = t = r2 + c2 와 같은 식이 성립하며
     * 이를 이용해 c2 = r1 + c1 - c2 와 같은 관계식을 만들어 3차원 dp로 문제 상황을 해결해 나간다.
     */
    static class Solution1 {
        int[][][] dp;
        int[][] grid;
        int N;

        public int cherryPickup(int[][] grid) { // solution 참고
            this.grid = grid;
            N = grid.length;
            dp = new int[N][N][N];

            for (int[][] layer : dp) {
                for (int[] row : layer) {
                    Arrays.fill(row, Integer.MIN_VALUE);
                }
            }

            return Math.max(0, dp(0, 0, 0));
        }

        public int dp(int r1, int c1, int c2) {
            int r2 = r1 + c1 - c2;
            if (N == r1 || N == r2 || N == c1 || N == c2 || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
                return -999999;
            } else if (r1 == N - 1 && c1 == N - 1) {
                return grid[r1][c1];
            } else if (dp[r1][c1][c2] != Integer.MIN_VALUE) {
                return dp[r1][c1][c2];
            } else {
                int ans = grid[r1][c1];
                if (c1 != c2) ans += grid[r2][c2]; // 같은 칸에 있는 경우 중복 방지
                ans += Math.max(Math.max(dp(r1, c1 + 1, c2 + 1), dp(r1 + 1, c1, c2 + 1)),
                                Math.max(dp(r1, c1 + 1, c2), dp(r1 + 1, c1, c2))
                );
                dp[r1][c1][c2] = ans;
                return ans;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
