package com.letscodefortest.codility;

// MissingInteger
public class Q1 {

    boolean[] check;

    public int solution(int[] A) {
        check = new boolean[1000001];

        for (int e : A) {
            if (e <= 0) continue;
            check[e] = true;
        }

        for (int i = 1; i < check.length; i++) {
            if (!check[i]) return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        System.out.println(q1.solution(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(q1.solution(new int[]{1, 2, 3}));
        System.out.println(q1.solution(new int[]{-1, -3}));
        int[] big_input = new int[1000001];
        for (int i = 1; i < big_input.length - 1; i++) {
            big_input[i] = i;
        }
        System.out.println(q1.solution(big_input));
    }
}
