package com.letscodefortest.medium;

// https://leetcode.com/problems/game-of-life
// solution 참고

/**
 * time complexity: O(MN)
 * space complexity: O(1)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
 * Memory Usage: 37.4 MB, less than 56.50% of Java online submissions for Game of Life.
 * 각 셀에 대해서 원래의 값이 어땠고 변경된 값이 어떻다는 의미를 동시에 나타내는 값을 설정해줘서 해당 셀의 값이 변경되도 그 히스토리를 알 수 있게 해줌으로서 저장공간을 추가로 사용하지 않고 알고리즘을 진행할 수 있다.
 * dummy cell 값을 활용하는 것
 * - 만약 값이 원래 1이었는데 0이 된 셀이라면 -1값을 준다
 * - 만약 값이 원래 0이었는데 1이 되었다면 값을 2로 변경한다
 */
public class Leetcode_q289 {
    public void gameOfLife(int[][] board) {
        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = row + neighbors[i];
                            int c = col + neighbors[j];

                            // Check the validity of the neighboring cell.
                            // and whether it was originally a live cell.
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }

                        }
                    }
                }
                // Rule 1 or Rule 3
                // -1 signifies the cell is now dead but originally was live.
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) board[row][col] = -1;
                // Rule 4
                // 2 signifies the cell is now live but was originally dead.
                if ((board[row][col] == 0) && liveNeighbors == 3) board[row][col] = 2;
            }
        }

        // Get the final representation for the newly updated board.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) board[row][col] = 1;
                else board[row][col] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
