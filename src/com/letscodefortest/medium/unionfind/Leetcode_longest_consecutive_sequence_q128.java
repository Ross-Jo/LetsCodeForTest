package com.letscodefortest.medium.unionfind;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_longest_consecutive_sequence_q128 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 13 ms, faster than 87.35% of Java online submissions for Longest Consecutive Sequence.
     * Memory Usage: 54.1 MB, less than 59.90% of Java online submissions for Longest Consecutive Sequence.
     * Set을 이용해 값을 넣고 시작지점을 파악후, 값을 하나씩 늘려가며 최대 값을 파악한다.
     */
    static class Solution1 {
        public int longestConsecutive(int[] nums) { // solution 참고
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            int maxLength = 0;

            for (int num : set) {
                if (!set.contains(num - 1)) {
                    int current = num;
                    int length = 1;

                    while (set.contains(current + 1)) {
                        current++;
                        length++;
                    }

                    maxLength = Math.max(maxLength, length);
                }
            }
            return maxLength;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
