package com.letscodefortest.medium.dfs;

import java.util.*;

// https://leetcode.com/problems/palindrome-partitioning/

public class Leetcode_palindrome_partitioning_q131 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();
    static Solution3 s3 = new Solution3();

    /**
     * time complexity: O(N^3 * N^2 / 2) TODO: 시간복잡도 검증
     * space complexity: O(N * 2^N)
     * Runtime: 36 ms, faster than 8.29% of Java online submissions for Palindrome Partitioning.
     * Memory Usage: 135.7 MB, less than 66.81% of Java online submissions for Palindrome Partitioning.
     * DP 방법을 이용해 char가 하나씩 추가될때마다 성립되는 팰린드롬이 있을지 생각해보고, 추가되는 partitioned case를 기록해 나간다
     */
    static class Solution1 {
        public List<List<String>> partition(String s) {
            Map<Integer, List<List<String>>> partitioned = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                // target string에 대해서 나올 수 있는 경우를 char 하나씩 늘려가면서 따져본다
                String targetSubstring = s.substring(0, i + 1);
                List<List<String>> newPartitionedResult = new ArrayList<>();

                // 기존의 partitioned result에 대해서 새로운 char를 하나의 팰린드롬이라고 쳤을 경우, 모두 성립 가능한 케이스이기 때문에 모두 추가
                updatePreviousResult(partitioned, i, targetSubstring, newPartitionedResult);

                if (i == 0) {
                    // 맨 처음의 경우 char 하나기 때문에 팰린드롬 성립으로 간주하고 추가
                    List<String> strings = new ArrayList<>();
                    strings.add(targetSubstring.substring(0, 1));
                    newPartitionedResult.add(strings);
                } else {
                    for (int j = i - 1; j >= 0; j--) {
                        // 추가되는 char로 인해 생성되는 팰린드롬이 있는지 따져보고,
                        if (targetSubstring.charAt(j) == targetSubstring.charAt(i)) {
                            // 만약 된다면 그 앞의 경우는 여전히 팰린드롬이 되는지 따져본다
                            if (j == 0) {
                                if (isPalindrome(targetSubstring.substring(j))) {
                                    List<String> strings = new ArrayList<>();
                                    strings.add(targetSubstring.substring(j));
                                    newPartitionedResult.add(strings);
                                }
                            } else {
                                if (isPalindrome(targetSubstring.substring(j)) && partitioned.getOrDefault(j - 1, null) != null) {
                                    for (List<String> strings : partitioned.get(j - 1)) {
                                        List<String> stringsCopy = new ArrayList<>();
                                        stringsCopy.addAll(strings);
                                        stringsCopy.add(targetSubstring.substring(j));
                                        newPartitionedResult.add(stringsCopy);
                                    }
                                }
                            }
                        }
                    }
                }

                partitioned.put(i, newPartitionedResult);
            }

            return partitioned.get(s.length() - 1);
        }

        private void updatePreviousResult(Map<Integer, List<List<String>>> partitioned, int index, String targetSubstring, List<List<String>> newResult) {
            List<List<String>> memoizedResult = partitioned.getOrDefault(index - 1, null);

            if (memoizedResult != null) {
                for (List<String> strings : memoizedResult) {
                    List<String> stringsCopy = new ArrayList<>();
                    stringsCopy.addAll(strings);
                    stringsCopy.add(targetSubstring.substring(index, index + 1));
                    newResult.add(stringsCopy);
                }
            }
        }

        public boolean isPalindrome(String s) {
            int len = s.length();

            if (len == 1) {
                return true;
            }

            char[] characters = s.toCharArray();

            for (int i = 0; i < len / 2; i++) {
                if (characters[i] != characters[(len - 1) - i]) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * time complexity: O(N^2 * 2^N)
     * space complexity: O(N)
     */
    static class Solution2 {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            dfs(0, result, new ArrayList<>(), s);
            return result;
        }

        void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
            if (start >= s.length()) result.add(new ArrayList<>(currentList));

            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    currentList.add(s.substring(start, end + 1));
                    dfs(end + 1, result, currentList, s);
                    currentList.remove(currentList.size() - 1);
                }
            }
        }

        boolean isPalindrome(String s, int low, int high) {
            while (low < high) {
                if (s.charAt(low++) != s.charAt(high--)) return false;
            }
            return true;
        }
    }

    /**
     * time complexity: O(N^2 * 2^N)
     * space complexity: O(N^2)
     */
    static class Solution3 {
        public List<List<String>> partition(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len][len];
            List<List<String>> result = new ArrayList<>();
            dfs(result, s, 0, new ArrayList<>(), dp);
            return result;
        }

        void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
            if (start >= s.length()) result.add(new ArrayList<>(currentList));

            for (int end = start; end < s.length(); end++) {
                if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                    currentList.add(s.substring(start, end + 1));
                    dfs(result, s, end + 1, currentList, dp);
                    currentList.remove(currentList.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        s1.partition("aaaaaaaaaaaaaaaaaaa");
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        s2.partition("aaaaaaaaaaaaaaaaaaa");
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
