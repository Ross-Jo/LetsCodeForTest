package com.letscodefortest.medium;

import java.util.*;

public class Leetcode_couples_holding_hands_q765 {
    static Solution1 s1 = new Solution1();

    // brute force
    static class Solution1 {
        int count;

        static class Couple {
            int a, b;

            public Couple(int a, int b) {
                this.a = a;
                this.b = b;
            }
        }

        public int minSwapsCouples(int[] row) {
            Deque<Couple> pool = new LinkedList<>();

            for (int i = 0; i < row.length; i = i + 2) {
                if (!isCouple(row[i], row[i + 1])) {
                    pool.add(new Couple(row[i], row[i + 1]));
                }
            }

            count = 0;

            while (!pool.isEmpty()) {
                Couple c1 = pool.poll();
                Couple c2 = pool.poll();

                if (!ableToSwap(c1, c2)) {
                    pool.add(c2);
                    pool.addFirst(c1);
                    continue;
                }

                if (!isCouple(c1.a, c1.b)) {
                    pool.add(c1);
                }
                if (!isCouple(c2.a, c2.b)) {
                    pool.add(c2);
                }
            }

            return count;
        }

        private boolean ableToSwap(Couple c1, Couple c2) {
            if (isCouple(c1.a, c2.a)) {
                int tmp = c1.b;
                c1.b = c2.a;
                c2.a = tmp;
                count++;
                return true;
            }
            if (isCouple(c1.a, c2.b)) {
                int tmp = c1.b;
                c1.b = c2.b;
                c2.b = tmp;
                count++;
                return true;
            }
            if (isCouple(c1.b, c2.a)) {
                int tmp = c1.a;
                c1.a = c2.a;
                c2.a = tmp;
                count++;
                return true;
            }
            if (isCouple(c1.b, c2.b)) {
                int tmp = c1.a;
                c1.a = c2.b;
                c2.b = tmp;
                count++;
                return true;
            }
            return false;
        }

        private boolean isCouple(int a, int b) {
            if (a % 2 == 0) {
                return b == a + 1;
            } else if (b % 2 == 0) {
                return a == b + 1;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(s1.minSwapsCouples(new int[]{0, 2, 1, 3}));
        System.out.println(s1.minSwapsCouples(new int[]{3, 2, 0, 1}));
        System.out.println(s1.minSwapsCouples(new int[]{0, 2, 4, 6, 7, 1, 3, 5}));
        System.out.println(s1.minSwapsCouples(new int[]{10, 7, 4, 2, 3, 0, 9, 11, 1, 5, 6, 8}));
    }
}
