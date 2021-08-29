package com.letscodefortest.medium;

import java.util.*;

public class Leetcode_17 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N^digits.length)
     * space complexity: O(N)
     * Runtime: 6 ms, faster than 10.90% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 39.4 MB, less than 16.97% of Java online submissions for Letter Combinations of a Phone Number.
     * Set<String>을 이용해 모든 결과를 조합한다.
     */
    static class Solution1 {
        String[] data = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) return new LinkedList<>();
            Set<String> set = new HashSet<>();

            char[] chars = digits.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                String letters = data[Character.getNumericValue(chars[i])];

                if (set.isEmpty()) {
                    for (int j = 0; j < letters.length(); j++) {
                        set.add(letters.charAt(j) + "");
                    }
                } else {
                    Set<String> tmp = new HashSet<>();
                    for (String e : set) {
                        for (int j = 0; j < letters.length(); j++) {
                            tmp.add(e + letters.charAt(j));
                        }
                    }
                    set = tmp;
                }
            }

            return new ArrayList<>(set);
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.letterCombinations("23"));
    }
}
