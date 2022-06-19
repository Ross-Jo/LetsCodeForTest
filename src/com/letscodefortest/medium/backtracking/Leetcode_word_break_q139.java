package com.letscodefortest.medium.backtracking;

import java.util.*;

public class Leetcode_word_break_q139 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    /**
     * time complexity: O(N^3)
     * space complexity: O(N)
     * Runtime: 2 ms, faster than 96.96% of Java online submissions for Word Break.
     * Memory Usage: 40.5 MB, less than 98.30% of Java online submissions for Word Break.
     * subproblem에 대한 memoization을 활용한다.
     */
    static class Solution1 {
        Map<Character, List<String>> dictByStartChar;
        String target;

        public boolean wordBreak(String s, List<String> wordDict) {
            target = s;
            dictByStartChar = new HashMap<>();

            wordDict.forEach(e -> {
                List<String> list = dictByStartChar.getOrDefault(e.charAt(0), new LinkedList<>());
                list.add(e);
                dictByStartChar.put(e.charAt(0), list);
            });

            return findSegmentation(0, new Boolean[s.length()]); // subproblem에 대한 memo를 할때 "문제를 접한적이 없음(=당면했던 subproblem)"이 아님을 나타내기 위해 Boolean(클래스) 배열을 사용한다.
        }

        public boolean findSegmentation(int startIndex, Boolean[] memo) {
            if (startIndex == target.length()) {
                return true;
            }

            if (memo[startIndex] != null) { // 만약 해결한적이 있는 subproblem이면 기존에 연산했던 결과를 반환한다
                return memo[startIndex];
            }

            List<String> list = dictByStartChar.get(target.charAt(startIndex));

            if (list != null) {
                for (String e : list) {
                    if (target.indexOf(e, startIndex) == startIndex) {
                        if(findSegmentation(startIndex + e.length(), memo)) {
                            return memo[startIndex] = true; // startIndex를 기준점으로 잡고, 그 뒷부분이 split 되는지 판단한 다음, memo해 놓는다.
                                                            // 이와 같은 memo 방식도 있다는 것을 봐둘 것
                        }
                    }
                }
            }
            return memo[startIndex] = false;
        }
    }

    static class Solution2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.wordBreak("leetcode", List.of(new String[]{"leet", "code"})));
        System.out.println(s1.wordBreak("applepenapple", List.of(new String[]{"apple", "pen"})));
        System.out.println(s1.wordBreak("catsandog", List.of(new String[]{"cats","dog","sand","and","cat"})));
        System.out.println(s1.wordBreak("cbca", List.of(new String[]{"bc", "ca"})));
        System.out.println(s1.wordBreak("cars", List.of(new String[]{"car", "ca", "rs"})));
        System.out.println(s1.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", List.of(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"})));
    }
}
