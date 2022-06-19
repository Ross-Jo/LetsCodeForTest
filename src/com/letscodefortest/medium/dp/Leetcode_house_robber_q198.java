package com.letscodefortest.medium.dp;

public class Leetcode_house_robber_q198 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
     * Memory Usage: 41.5 MB, less than 53.09% of Java online submissions for House Robber.
     * 단순 DP 문제
     */
    static class Solution1 {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }

            int[] dp = new int[nums.length];

            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < dp.length ; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            return dp[nums.length - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.rob(new int[]{1, 2, 3, 1}));
        System.out.println(s1.rob(new int[]{2,7,9,3,1}));
    }
}
