package com.letscodefortest.medium.rule;

// https://leetcode.com/problems/longest-palindromic-substring/

public class Leetcode_longest_palindromic_substring_q5 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N^3)
     * space complexity: O(N^2)
     * Runtime: 418 ms, faster than 26.47% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 45.1 MB, less than 52.68% of Java online submissions for Longest Palindromic Substring.
     * 이미 연산했던 부분에 대한 재연산을 하지 않기 위해 DP사용
     */
    static class Solution1 {

        public boolean[][] dp;

        public String longestPalindrome(String s) {
            int len = s.length();
            dp = new boolean[len + 1][len + 1];

            for (int size = len; size >= 1; size--) {
                for (int j = 0; j < len - size + 1; j++) {
                    if (isPalindrome(j, j + size - 1, s)) {
                        return s.substring(j, j + size);
                    }
                }
            }

            return s.substring(0, 1);
        }


        private boolean isPalindrome(int i, int j, String s) {
            if (i >= j) {
                return true;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            if (dp[i + 1][j - 1]) {
                return true;
            } else {
                return dp[i][j] = isPalindrome(i + 1, j - 1, s);
            }
        }
    }

    /**
     * time complexity: O(N^2)
     * space complexity: O(1)
     * Runtime: 36 ms, faster than 86.55% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 43 MB, less than 77.39% of Java online submissions for Longest Palindromic Substring.
     * 가운데서 부터 확장해나가서, 팰린드롬의 길이를 구한다.
     */
    static class Solution2 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i); // 팰린드롬의 길이가 홀수인경우와
                int len2 = expandAroundCenter(s, i, i + 1); // 짝수일 경우를 모두 따져준다.
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.longestPalindrome("a"));
        int num = 1 / 2;
        System.out.println(num);
    }
}
