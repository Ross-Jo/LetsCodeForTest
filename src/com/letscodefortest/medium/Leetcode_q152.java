package com.letscodefortest.medium;

// https://leetcode.com/problems/maximum-product-subarray/


/**
 * time complexity: O(N)
 * space complexity: O(1)
 * Runtime: 1 ms, faster than 90.82% of Java online submissions for Maximum Product Subarray.
 * Memory Usage: 38.2 MB, less than 98.81% of Java online submissions for Maximum Product Subarray.
 * 현재값, 현재까지의 최소값, 현재까지의 최대값을 이용하여 부분 Array의 최대 곱을 산출한다.
 */
public class Leetcode_q152 {
    Solution1 s1 = new Solution1();

    // leetcode solution
    static class Solution1 {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) return 0;

            int max_so_far = nums[0];
            int min_so_far = nums[0];
            int result = max_so_far;

            for (int i = 1; i < nums.length; i++) {
                int curr = nums[i];
                int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));

                min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));
                max_so_far = temp_max;

                result = Math.max(max_so_far, result);
            }

            return result;
        }
    }

    public static void main(String[] args) {

    }

}
