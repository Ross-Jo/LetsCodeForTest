package com.letscodefortest.medium.rule.rollinghash;

import java.util.*;
import java.util.stream.Stream;

// https://leetcode.com/problems/repeated-dna-sequences/
public class Leetcode_repeated_dna_sequences_q187 {

    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 49 ms, faster than 34.69% of Java online submissions for Repeated DNA Sequences.
     * Memory Usage: 52.1 MB, less than 82.15% of Java online submissions for Repeated DNA Sequences.
     * 주어진 String에 대한 4진법 변환을 이용해 해시값 계산 및 갱신. 이를 이용해 2번 이상 목격한 수를 찾는다
     */
    static class Solution1 {
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10, n = s.length();
            if (n < 10) return new ArrayList<>();

            int a = 4, aL = (int) Math.pow(a, 10);
            Map<Character, Integer> toInt = new HashMap<>() {{
                put('A', 0);
                put('C', 1);
                put('G', 2);
                put('T', 3);
            }};
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = toInt.get(s.charAt(i));
            }

            int h = 0;
            Set<Integer> seen = new HashSet<>();
            Set<String> output = new HashSet<>();

            for (int start = 0; start < n - L + 1; start++) {
                if (start != 0) {
                    h = h * a - nums[start - 1] * aL + nums[start + L - 1]; // loop를 돌면서 이전 해시값을 이용해 다음 해시값을 계산함.
                    // 이때, 해시값 자체를 4진법을 이용해서 계산했기 떄문에, 4진법의 최고자리 숫자를 제외하고, 새로운 숫자를 덧붙이는 식으로 해시값을 갱신.
                } else {
                    for (int i = 0; i < L; i++) {
                        h = h * a + nums[i]; // start == 0 즉, 처음의 경우 해시값 초기화
                    }
                }
                if (seen.contains(h)) output.add(s.substring(start, start + L)); // 만약 한번 이상 목격 된 해시값이라면 output에 입력
                seen.add(h);
            }

            return new ArrayList<>(output);
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }
}
