package com.letscodefortest.medium.rule;

import java.util.Arrays;

// https://leetcode.com/problems/prison-cells-after-n-days/

public class Leetcode_prison_cells_after_n_days_q957 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(n)
     * space complexity: O(1)
     * Runtime: 1 ms, faster than 98.71% of Java online submissions for Prison Cells After N Days.
     * Memory Usage: 43.1 MB, less than 62.23% of Java online submissions for Prison Cells After N Days.
     * 문제의 조건에 맞게 0, 1 값을 넣어주되 반복되는 구간은 제거한다
     */
    static class Solution1 {
        public int[] prisonAfterNDays(int[] cells, int n) {
            n = n % 14 == 0 ? 14 : n % 14;

            for (int i = 0; i < n; i++) {
                cells = filp(cells);
            }

            return cells;
        }

        public int[] filp(int[] cells) {
            int[] ret = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    ret[i] = 0;
                } else {
                    if(cells[i-1] == cells[i+1]) {
                        ret[i] = 1;
                    } else {
                        ret[i] = 0;
                    }
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(s1.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 100)));
//        System.out.println(Arrays.toString(s1.prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000)));
        System.out.println(Arrays.toString(s1.prisonAfterNDays(new int[]{1,1,0,1,1,0,0,1}, 100)));
    }
}
