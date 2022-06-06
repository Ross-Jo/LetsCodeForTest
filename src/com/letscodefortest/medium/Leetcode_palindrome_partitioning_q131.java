package com.letscodefortest.medium;

import java.util.*;

public class Leetcode_palindrome_partitioning_q131 {
    static Solution1 s1 = new Solution1();

    static class Solution1 {
        public List<List<String>> partition(String s) {
            /**
             * K라는 값을 추가했을때를 생각해보자
             * - case1. k라는 letter를 추가 했을때, 기존 patitioning case + 'k'
             * - case2. k로 인해서 비로소 palindrome이 있는 경우가 일단 존재하는지 확인후
             *          - 나머지 letter 들에서 palindrome partitioning이 성립하는지 확인한다
             *          - 그런데 이때 해당 partitioning이 되는지는 이미 기존에 구해 놨을 것이다
             *
             * - 그래서 항상 DP의 이슈는 점화식을 어떻게 세우냐 인데
             * - sub problem을 어떻게 정의하느냐...
             *
             * 해결해야 하는 문제 = [[해결해야 하는문제 - 1]의 경우, 'k'] + [k로 인해 palindrome이 되는 경우 && [해결해야 하는 문제 - (k로 인해 palindrome이 되는 경우의 index)]]
             *
             * map
             * key = 다루는 last index
             * value = 다루는 last index 까지 도출해낸 palindrome 파티셔닝 케이스 List<List<String>>
             *
             * 그렇다면 k로 인해 팰린드롬이 되는 케이스는 어떻게 생각하지?
             * k가 마지막 이라는 얘기니까 앞의 letter중 k를 찾아서 마지막 k까지의 substring을 팰린드롬 검증함수에 넣고 돌린다
             *
             */

            Map<Integer, List<List<String>>> partitioned = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                String target = s.substring(0, i + 1);

                List<List<String>> newResult = new ArrayList<>();

                List<List<String>> memoizedResult = partitioned.getOrDefault(i - 1, null);

                if (memoizedResult != null) {
                    for (List<String> strings : memoizedResult) {
                        List<String> stringsCopy = new ArrayList<>();
                        stringsCopy.addAll(strings);
                        stringsCopy.add(target.substring(i, i + 1));
                        newResult.add(stringsCopy);
                    }
                }

                if (i == 0) {
                    List<String> strings = new ArrayList<>();
                    strings.add(target.substring(0, 1));
                    newResult.add(strings);
                } else {
                    for (int j = i - 1; j >= 0; j--) {
                        if (target.charAt(j) == target.charAt(i)) {
                            if (j - 1 < 0) {
                                if (isPalindrome(target.substring(j))) {
                                    List<String> strings = new ArrayList<>();
                                    strings.add(target.substring(j));
                                    newResult.add(strings);
                                }
                            } else {
                                if (isPalindrome(target.substring(j)) && partitioned.getOrDefault(j - 1, null) != null) {
                                    for (List<String> strings : partitioned.get(j - 1)) {
                                        List<String> stringsCopy = new ArrayList<>();
                                        stringsCopy.addAll(strings);
                                        stringsCopy.add(target.substring(j));
                                        newResult.add(stringsCopy);
                                    }
                                }
                            }
                        }
                    }
                }

                partitioned.put(i, newResult);
            }

            return partitioned.get(s.length() - 1);
        }

        public boolean isPalindrome(String s) {
            int len = s.length();
            if (len == 1) return true;
            char[] characters = s.toCharArray();

            for (int i = 0; i < len / 2; i++) {
                if (characters[i] != characters[(len - 1) - i]) {
                    return false;
                }
            }

            return true;
        }

    }

    public static void main(String[] args) {
        System.out.println(s1.partition("aabaa"));
    }
}
