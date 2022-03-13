package com.letscodefortest.medium;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/

public class Leetcode_q3 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(M), m = charset의 size
     * Runtime: 4 ms, faster than 95.92% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 43.7 MB, less than 60.11% of Java online submissions for Longest Substring Without Repeating Characters.
     * sliding window 방법을 사용해, 오른쪽 끝을 늘려가 주면서, 왼쪽 끝이 변경되어야 하는지를 판단한다.
     * 이때 왼쪽 끝이 변경되어야 하는 판단 기준은 한번이라도 출몰한 char의 index 다음 index를 저장해, 한번이라도 출몰한 char와 동일한 char가 출현하는 경우
     * (이전에 등장한) 해당 char의 다음 시작 지점으로 i를 옮겨주는 것이다 (substring에 두번 중복되는 char가 없도록)
     *
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     *
     */
    static class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            int[] index = new int[128];

            for (int i = 0, j = 0; j < n; j++) {
                i = Math.max(index[s.charAt(j)], i);
                ans = Math.max(ans, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s1.lengthOfLongestSubstring("bbbbb"));
        System.out.println(s1.lengthOfLongestSubstring("pwwkew"));
        System.out.println(s1.lengthOfLongestSubstring("dvdf"));
    }
}
