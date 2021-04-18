package com.letscodefortest.medium;

// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.HashMap;

public class Leetcode_q560 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    static class Solution1 {
        public int subarraySum(int[] nums, int k) {
            int[] memo = new int[nums.length];

            memo[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                memo[i] = memo[i-1] + nums[i];
            }

            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    if (i == 0) {
                        if (k == memo[j]) cnt++;
                    } else {
                        if (k == memo[j]-memo[i-1]) cnt++;
                    }
                }
            }
            return cnt;
        }
    }

    static class Solution2 {
        // better code than solution1
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            int[] sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int i = 1; i <= nums.length; i++)
                sum[i] = sum[i - 1] + nums[i - 1];
            for (int start = 0; start < nums.length; start++) {
                for (int end = start + 1; end <= nums.length; end++) {
                    if (sum[end] - sum[start] == k)
                        count++;
                }
            }
            return count;
        }
    }

    static class Solution3 {
        // better code than solution2
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            for (int start = 0; start < nums.length; start++) {
                int sum=0;
                for (int end = start; end < nums.length; end++) {
                    sum+=nums[end];
                    if (sum == k)
                        count++;
                }
            }
            return count;
        }
    }

    static class Solution4 { // 주목해야 할 솔루션
        public int subarraySum(int[] nums, int k) {
            int count = 0, sum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum - k)) count += map.get(sum - k);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println("ans:" + s1.subarraySum(new int[]{1,2,3}, 3));
    }
}
