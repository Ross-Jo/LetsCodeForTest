package com.letscodefortest.medium.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode_01_matrix_q542 {

    static Solution1 s1 = new Solution1();
    static Solution1 s2 = new Solution1();
    static Solution1 s3 = new Solution1();

    // 완전탐색
    static class Solution1 {
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            if (rows == 0) {
                return mat;
            }
            int cols = mat[0].length;
            int[][] dist = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        dist[i][j] = 0;
                    } else {
                        for (int k = 0; k < rows; k++) {
                            for (int l = 0; l < cols; l++) {
                                dist[i][j] = Math.min(dist[i][j], Math.abs(k - i) + Math.abs(l - j));
                            }
                        }
                    }
                }
            }
            return dist;
        }
    }

    /**
     * time complexity: O(r*c)
     * space complexity: O(r*c)
     * Runtime: 25 ms, faster than 42.19% of Java online submissions for 01 Matrix.
     * Memory Usage: 72.5 MB, less than 54.40% of Java online submissions for 01 Matrix.
     * BFS를 통해서 0인 cell부터 시작하여 0과의 least distance를 업데이트 한다. 이때 포인트는 2차원 배열을 최대값으로 채우고, 계산된 값이 작을 때에만 업데이트를 한다는 것이다
     */
    static class Solution2 {

        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            if (rows == 0) {
                return mat;
            }
            int cols = mat[0].length;
            int[][] dist = new int[rows][cols];
            for (int[] e : dist) {
                Arrays.fill(e, Integer.MAX_VALUE); // 초기값을 MAX로 세팅
            }
            Queue<Point> q = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        dist[i][j] = 0;
                        q.add(new Point(i, j));
                    }
                }
            }

            int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (!q.isEmpty()) {
                Point curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    int new_r = curr.x + dir[i][0], new_c = curr.y + dir[i][1];
                    if (new_r >= 0 && new_c >= 0 && new_r < rows && new_c < cols) {
                        if (dist[new_r][new_c] > dist[curr.x][curr.y] + 1) { // 더 작은 값으로만 업데이트
                            dist[new_r][new_c] = dist[curr.x][curr.y] + 1;
                            q.add(new Point(new_r, new_c));
                        }
                    }
                }
            }
            return dist;
        }
    }

    /**
     * time complexity: O(r*c)
     * space complexity: O(r*c)
     * Runtime: 13 ms, faster than 75.71% of Java online submissions for 01 Matrix.
     * Memory Usage: 72.5 MB, less than 51.31% of Java online submissions for 01 Matrix.
     * 2차원 배열 DP시 2차원 Array를 큰수로 초기화 하고, 0값을 세팅, 왼쪽/위쪽 방향 기준으로 1차 DP, 아래쪽/오른쪽 방향 기준으로 2차 DP를 했다는 점이 포인트
     * 최초 시작값을 모르기 때문에 적당히 큰 수로 초기화 하고 양방향 DP를 통해 DP solution을 도출한점이 인상적
     */
    static class Solution3 {
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length;
            if (rows == 0) {
                return mat;
            }
            int cols = mat[0].length;
            int[][] dist = new int[rows][cols];
            for (int[] e : dist) {
                Arrays.fill(e, Integer.MAX_VALUE - 100000); // 초기값을 큰 수로 세팅
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (mat[i][j] == 0) {
                        dist[i][j] = 0;
                    } else {
                        if (i > 0)
                            dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                        if (j > 0)
                            dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                    }
                }
            }

            for (int i = rows - 1; i >= 0; i--) {
                for (int j = cols - 1; j >= 0; j--) {
                    if (i < rows - 1)
                        dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                    if (j < cols - 1)
                        dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
            return dist;
        }
    }

    public static void main(String[] args) {

    }
}
