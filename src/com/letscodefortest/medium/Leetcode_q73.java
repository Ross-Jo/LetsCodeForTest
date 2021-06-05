package com.letscodefortest.medium;

// https://leetcode.com/problems/set-matrix-zeroes/

public class Leetcode_q73 {
    /**
     * time complexity: O(NM)
     * space complexity: O(1)
     * Runtime: 1 ms, faster than 92.60% of Java online submissions for Set Matrix Zeroes.
     * Memory Usage: 40.7 MB, less than 26.70% of Java online submissions for Set Matrix Zeroes.
     * 첫 행 및 열을 sentinal로 삼아 저장공간을 추가적으로 사용하지 않으면서 문제를 해결하는 방식
     */
    void setZeroes(int[][] matrix) {
        int firstCol = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j == 0) {
                        firstCol = 0;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) setColZero(i, matrix);
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) setRowZero(i, matrix);
        }

        if (matrix[0][0] == 0) {
            setRowZero(0, matrix);
        }

        if (firstCol == 0) {
            setColZero(0, matrix);
        }
    }

    void setColZero(int col, int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    void setRowZero(int row, int[][] matrix){
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }

    public static void main(String[] args) {

    }
}
