package com.letscodefortest.medium.string.kmp;

// https://leetcode.com/problems/implement-strstr/

public class Leetcode_implement_strstr_q28 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();
    static Solution3 s3 = new Solution3();

    // Java 내장함수 사용
    static class Solution1 {
        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }

    // 완전 탐색
    static class Solution2 {
        public int strStr(String haystack, String needle) {
            int haystackLength = haystack.length();
            int needleLength = needle.length();

            int maxIndex = haystackLength - needleLength;

            for (int i = 0; i <= maxIndex; i++) {
                for (int j = 0; j < needleLength; j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) break;
                    if (j == needleLength - 1) return i;
                }
            }
            return -1;
        }
    }

    // KMP 알고리즘 - 참고 : https://bowbowbow.tistory.com/6
    static class Solution3 {
        int[] pi;

        public int strStr(String haystack, String needle) {
            pi = new int[needle.length()];
            getPi(needle);

            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = pi[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    if (j == needle.length() - 1) {
                        j = pi[j]; // 사용하지는 않지만 학습을 위해서
                        return i - needle.length() + 1;
                    } else {
                        j++;
                    }
                }
            }


            return -1;
        }

        private void getPi(String needle) {
            int j = 0;
            for (int i = 1; i < needle.length(); i++) {
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = pi[j - 1];
                }
                if (needle.charAt(i) == needle.charAt(j)) {
                    pi[i] = ++j;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(s3.strStr("hello", "ll"));
    }
}
