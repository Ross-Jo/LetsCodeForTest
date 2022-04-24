package com.letscodefortest.medium.rule;

public class Leetcode_Monotone_Increasing_Digits_q738 {

    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 1 ms, faster than 96.28% of Java online submissions for Monotone Increasing Digits.
     * Memory Usage: 42.1 MB, less than 7.12% of Java online submissions for Monotone Increasing Digits.
     * 단조 증가가 깨지는 구간에서 자릿수를 하나 내리고 나머지 자리를 9로 채운다 - 해설참조
     */
    static class Solution1 {
        public int monotoneIncreasingDigits(int n) {
            char[] arr = String.valueOf(n).toCharArray();

            int target = arr.length - 1;
            for (int i = arr.length - 1; i > 0; i--) {
                if (arr[i] < arr[i - 1]) {
                    target = i - 1;
                    arr[i - 1]--;
                }
            }
            for (int i = target + 1; i < arr.length; i++) {
                arr[i] = '9';
            }
            return Integer.parseInt(new String(arr));
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.monotoneIncreasingDigits(10));
    }
}
