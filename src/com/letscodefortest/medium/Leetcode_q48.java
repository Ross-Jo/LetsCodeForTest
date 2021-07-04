package com.letscodefortest.medium;

// https://leetcode.com/problems/rotate-image
// solution : https://www.youtube.com/watch?v=bW_9pjcXP_4

/**
 * time complexity: O(N)
 * space complexity: O(1)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
 * Memory Usage: 38.9 MB, less than 76.71% of Java online submissions for Rotate Image.
 * 셀을 4개의 group으로 나누고 순차적으로 회전 시킴
 */
public class Leetcode_q48 {
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                System.out.println("i: " + i + ", j: " + j);
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse of rows
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        int layers = n / 2;

        for (int layer = 0; layer < layers; layer++) {
            int start = layer;
            int end = n - 1 - layer;

            for (int i = start; i < end; i++) {

                // top in temp
                int temp = matrix[start][i];

                // left in top
                matrix[start][i] = matrix[n - 1 - i][start];

                // bottom in left
                matrix[n - 1 - i][start] = matrix[end][n - 1 - i];

                // right in bottom
                matrix[end][n - 1 - i] = matrix[i][end];

                // top in right
                matrix[i][end] = temp;
            }
        }
    }

    public void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0}
        };

        int[][] matrix2 = new int[][]{
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25},
                {26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35}
        };

        Leetcode_q48 q48 = new Leetcode_q48();
        q48.rotate1(matrix2);
        q48.print(matrix2);
    }
}
