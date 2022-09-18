package com.letscodefortest.medium.rule;

import java.util.*;

public class Leetcode_valid_sudoku_q36 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();
    static Solution3 s3 = new Solution3();
    static Solution4 s4 = new Solution4();

    /**
     * time complexity: O(N^2)
     * space complexity: O(N)
     * Runtime: 4 ms, faster than 41.27% of Java online submissions for Valid Sudoku.
     * Memory Usage: 39.1 MB, less than 58.79% of Java online submissions for Valid Sudoku.
     * Set을 이용해 스도쿠 조건이 맞는지 확인한다. 사실상 brute force
     */
    static class Solution1 {
        public boolean isValidSudoku(char[][] board) {
            boolean result = true;

            for (char[] chars : board) {
                result &= check(chars);
            }

            for (int i = 0; i < board[0].length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < board.length; j++) {
                    sb.append(board[j][i]);
                }
                result &= check(sb.toString().toCharArray());
            }

            for (int i = 0; i < board.length; i = i + 3) {
                for (int j = 0; j < board[0].length; j = j + 3) {

                    StringBuilder sb = new StringBuilder();
                    for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            sb.append(board[k][l]);
                        }
                    }
                    result &= check(sb.toString().toCharArray());
                }
            }

            return result;
        }

        public boolean check(char[] chars) {
            Set<Character> validSubSet = new HashSet<>();
            for (char c : chars) {
                if (c == '.') continue;
                if (c < '1' || c > '9') return false;
                if (validSubSet.contains(c)) {
                    return false;
                } else {
                    validSubSet.add(c);
                }
            }
            return true;
        }
    }

    /**
     * time complexity: O(N^2)
     * space complexity: O(N^2)
     * Runtime: 5 ms, faster than 36.98% of Java online submissions for Valid Sudoku.
     * Memory Usage: 45 MB, less than 5.09% of Java online submissions for Valid Sudoku.
     * 역시 Set을 이용해 스도쿠 조건이 맞는지 확인한다. 사실상 brute force. 하지만 box의 index를 구하는 부분이 포인트
     */
    static class Solution2 { // solution 참고
        public boolean isValidSudoku(char[][] board) {
            int N = 9;

            HashSet<Character>[] rows = new HashSet[N];
            HashSet<Character>[] cols = new HashSet[N];
            HashSet<Character>[] boxes = new HashSet[N];

            for (int r = 0; r < N; r++) {
                rows[r] = new HashSet<>();
                cols[r] = new HashSet<>();
                boxes[r] = new HashSet<>();
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    char val = board[r][c];
                    if (val == '.') {
                        continue;
                    }

                    if (rows[r].contains(val)) {
                        return false;
                    }
                    rows[r].add(val);

                    if (cols[c].contains(val)) {
                        return false;
                    }
                    cols[c].add(val);

                    int idx = (r / 3) * 3 + c / 3; // 어떻게 box의 인덱스를 구했는지를 살펴보자
                    if (boxes[idx].contains(val)) {
                        return false;
                    }
                    boxes[idx].add(val);
                }
            }
            return true;
        }
    }

    /**
     * time complexity: O(N^2)
     * space complexity: O(N^2)
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Valid Sudoku.
     * Memory Usage: 39.4 MB, less than 43.46% of Java online submissions for Valid Sudoku.
     * array 표시를 통한 중복확인 - solution4를 위한 중간단계
     */
    static class Solution3 { // solution 참고
        public boolean isValidSudoku(char[][] board) {
            int N = 9;

            int[][] rows = new int[N][N];
            int[][] cols = new int[N][N];
            int[][] boxes = new int[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.') {
                        continue;
                    }
                    int pos = board[r][c] - '1';

                    if (rows[r][pos] == 1) {
                        return false;
                    }
                    rows[r][pos] = 1;

                    if (cols[c][pos] == 1) {
                        return false;
                    }
                    cols[c][pos] = 1;

                    int idx = (r / 3) * 3 + c / 3;
                    if (boxes[idx][pos] == 1) {
                        return false;
                    }
                    boxes[idx][pos] = 1;
                }
            }
            return true;
        }
    }

    /**
     * time complexity: O(N^2)
     * space complexity: O(N)
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Valid Sudoku.
     * Memory Usage: 38.4 MB, less than 98.85% of Java online submissions for Valid Sudoku.
     * bit operation을 통한 중복확인
     */
    static class Solution4 { // solution 참고
        public boolean isValidSudoku(char[][] board) {
            int N = 9;

            int[] rows = new int[N];
            int[] cols = new int[N];
            int[] boxes = new int[N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.') {
                        continue;
                    }
                    int val = board[r][c] - '0';
                    int pos = 1 << (val - 1); // 비트연산을 통한 중복체크가 인상적인 부분

                    if ((rows[r] & pos) > 0) {
                        return false;
                    }
                    rows[r] |= pos;

                    if ((cols[c] & pos) > 0) {
                        return false;
                    }
                    cols[c] |= pos;

                    int idx = (r / 3) * 3 + c / 3;
                    if ((boxes[idx] & pos) > 0) {
                        return false;
                    }
                    boxes[idx] |= pos;
                }
            }
            return true;
        }
    }


    public static void main(String[] args) {
        char[][] input = new char[][]
                 {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(s1.isValidSudoku(input));
    }
}
