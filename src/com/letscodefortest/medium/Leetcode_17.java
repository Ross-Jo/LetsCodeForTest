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

    /**
     * time complexity: O(4^N * N) N: length of digits
     * space complexity: O(N)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 37.6 MB, less than 86.72% of Java online submissions for Letter Combinations of a Phone Number.
     * backtrack을 이용해 모든 결과를 조합한다.
     */
    static class Solution2 { // solution 참고
        private List<String> combinations = new ArrayList<>();
        private Map<Character, String> letters
                = Map.of('2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
        private String phoneDigits;

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) return combinations;

            phoneDigits = digits;
            backtrack(0, new StringBuilder());
            return combinations;
        }

        private void backtrack(int index, StringBuilder path) {
            if (path.length() == phoneDigits.length()) {
                combinations.add(path.toString());
                return;
            }

            String possibleLetters = letters.get(phoneDigits.charAt(index));
            for (char letter : possibleLetters.toCharArray()) {
                path.append(letter);
                backtrack(index + 1, path);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.letterCombinations("23"));
    }
}
