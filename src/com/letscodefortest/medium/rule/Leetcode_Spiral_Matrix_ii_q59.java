package com.letscodefortest.medium.rule;

public class Leetcode_Spiral_Matrix_ii_q59 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
     * Memory Usage: 40 MB, less than 98.97% of Java online submissions for Spiral Matrix II.
     * 규칙에 따라 matrix를 순회하면서 숫자를 채워나간다
     */
    static class Solution1 {

        int[] xs = new int[]{0, 1, 0, -1}; // 차례로 오른쪽, 아래쪽, 왼쪽, 위쪽 방향
        int[] ys = new int[]{1, 0, -1, 0};

        public int[][] generateMatrix(int n) {
            int[][] ret = new int[n][n];

            int num = 1; // 채워나갈 숫자
            int direction = 0; // 방향을 나타내는 index

            int round = 2 * n - 1; // 한 방향으로 채워 나가는 한개의 턴을 한 라운드라고 했을때 몇개의 라운드가 있는지
            int reachCalcHelperValue = 1; // 각 라운드에서 채워야할 칸수가 몇 칸인지 계산을 돕기 위한 변수 (5x5 matrix면, 각 라운드는 5, 4, 4, 3, 3, 2, 2, 1, 1 인데 해당 숫자들을 구하기 위한 helper 변수)

            int x = 0, y = -1; // 이동 규칙 때문에 (0, -1)에서 시작한다

            for (int i = 0; i < round; i++) {
                int reach;

                if (i == 0) {
                    reach = n; // 첫 라운드는 예외적 규칙
                } else {
                    reach = (int) Math.ceil((round - reachCalcHelperValue++) / 2.0); // (5x5 matrix면, 4, 4, 3, 3, 2, 2, 1, 1과 같은 숫자를 계산)
                }

                for (int j = 0; j < reach; j++) { // 라운드 별로 리치번씩 방향 이동후 숫자 기입
                    x += xs[direction];
                    y += ys[direction];
                    ret[x][y] = num++;
                }
                direction = (direction + 1) % 4; // 방향 전환
            }

            return ret;
        }

        public void printer(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.printf("%3d", matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        s1.printer(s1.generateMatrix(5));
    }
}
