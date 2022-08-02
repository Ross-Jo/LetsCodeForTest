package com.letscodefortest.medium.bit;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/single-number

public class Leetcode_single_number_q136 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 24 ms, faster than 12.43% of Java online submissions for Single Number.
     * Memory Usage: 54.4 MB, less than 12.43% of Java online submissions for Single Number.
     * 아래 해결책의 경우 공간 복잡도를 만족시키지 못함
     */
    static class Solution1 {
        public int singleNumber(int[] nums) {
            Set<Integer> bucket = new HashSet<>();
            for (int i : nums) {
                if (!bucket.contains(i)) {
                    bucket.add(i);
                } else {
                    bucket.remove(i);
                }
            }
            return bucket.stream().reduce(0, Integer::sum);
        }
    }

    // 시간: O(N), 공간 O(1) : exclusive or를 이용한 방법
    static class Solution2 {
        public int singleNumber(int[] nums) {
            int a = 0;
            for (int i : nums) {
                a ^= i;
            }
            return a;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.singleNumber(new int[]{1, 1, 2}));
    }
}
