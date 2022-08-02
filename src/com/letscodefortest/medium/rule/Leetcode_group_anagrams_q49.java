package com.letscodefortest.medium.rule;

import java.util.*;

// https://leetcode.com/problems/group-anagrams

public class Leetcode_group_anagrams_q49 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(NKlogK)
     * space complexity: O(NK)
     * Runtime: 7 ms, faster than 95.95% of Java online submissions for Group Anagrams.
     * Memory Usage: 46 MB, less than 88.53% of Java online submissions for Group Anagrams.
     */
    static class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> bucket = new HashMap<>();

            for (String e : strs) {
                char[] chars = e.toCharArray();
                Arrays.sort(chars);

                String key = new String(chars);

                List<String> res = bucket.getOrDefault(key, new ArrayList<>());
                res.add(e);

                bucket.putIfAbsent(key, res);
            }
            return new ArrayList<>(bucket.values());
        }
    }

    static class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs.length == 0) return new ArrayList<>();
            Map<String, ArrayList<String>> ans = new HashMap<>();
            int[] count = new int[26];

            for (String s : strs) {
                Arrays.fill(count, 0);

                for (char c : s.toCharArray()) {
                    count[c - 'a']++;
                }

                StringBuilder sb = new StringBuilder("");

                for (int i = 0; i < 26; i++) {
                    sb.append('#');
                    sb.append(count[i]);
                }

                String key = sb.toString();
                if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
                ans.get(key).add(s);
            }
            return new ArrayList<>(ans.values());
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.groupAnagrams(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"}));

    }
}
