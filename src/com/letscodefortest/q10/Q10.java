package com.letscodefortest.q10;

// question : https://leetcode.com/problems/dungeon-game/

import java.util.Arrays;

public class Q10 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N*M)
     * space complexity: O(N*M)
     * Runtime: 2 ms, faster than 50.84% of Java online submissions for Dungeon Game.
     * Memory Usage: 38.4 MB, less than 91.41% of Java online submissions for Dungeon Game.
     * 마지막지점에서 시작해 시작지점으로 경로를 거슬러 오면서 dp 수행
     */
    static class Solution1 {
        int inf = Integer.MAX_VALUE;
        int[][] dp;
        int rows, cols;

        public int getMinHealth(int currCell, int nextRow, int nextCol) {
            if (nextRow >= this.rows || nextCol >= this.cols) return inf;
            int nextCell = this.dp[nextRow][nextCol];
            return Math.max(1, nextCell - currCell);
        }

        public int calculateMinimumHP(int[][] dungeon) {
            this.rows = dungeon.length;
            this.cols = dungeon[0].length;
            this.dp = new int[rows][cols];
            for (int[] arr : this.dp) Arrays.fill(arr, this.inf);

            int currCell, rightHealth, downHealth, nextHealth, minHealth;
            for (int row = this.rows - 1; row >= 0; --row) {
                for (int col = this.cols - 1; col >= 0; --col) {
                    currCell = dungeon[row][col];

                    rightHealth = getMinHealth(currCell, row, col + 1);
                    downHealth = getMinHealth(currCell, row + 1, col);
                    nextHealth = Math.min(rightHealth, downHealth);

                    if (nextHealth != inf) {
                        minHealth = nextHealth;
                    } else {
                        minHealth = currCell >= 0 ? 1 : 1 - currCell;
                    }
                    this.dp[row][col] = minHealth;
                }
            }

            return this.dp[0][0];
        }
    }

    public static void main(String[] args) {
//        System.out.println(s1.calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
//        System.out.println(s1.calculateMinimumHP(new int[][]{{0}}));
//        System.out.println(s1.calculateMinimumHP(new int[][]{{0,-3}}));
//        System.out.println(s1.calculateMinimumHP(new int[][]{{-3,5}}));
//        System.out.println(s1.calculateMinimumHP(new int[][]{{100}}));
        System.out.println(s1.calculateMinimumHP(new int[][]{{0, 5}, {-2, -3}}));
    }
}
