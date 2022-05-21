package com.letscodefortest.medium.dp;

import java.util.Arrays;

// https://leetcode.com/problems/partition-equal-subset-sum/

public class Leetcode_partition_equal_subset_sum_q416 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(m * n), m = halfSum, n = number of elements
     * space complexity: O(m)
     * Runtime: 31 ms, faster than 77.30% of Java online submissions for Partition Equal Subset Sum.
     * Memory Usage: 42.6 MB, less than 82.64% of Java online submissions for Partition Equal Subset Sum.
     * 근본적으로 knapsack 문제와 동일한 dp 문제인데 해결책을 빠르게 도출해내지 못함. 복습필요
     */
    static class Solution1 {
        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            if (sum % 2 == 1) {
                return false;
            }
            int halfSum = sum / 2;
            boolean[] memo = new boolean[halfSum + 1];
            memo[0] = true;

            for (int curr : nums) {
                for (int j = halfSum; j >= curr; j--) {
                    memo[j] |= memo[j - curr];
                }
            }
            
            return memo[halfSum];
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.canPartition(new int[]{1, 5, 11, 5}));
    }
}
