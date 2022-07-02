package com.letscodefortest.medium.rule;

public class Leetcode_non_decreasing_array_q665 {

    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 1 ms, faster than 96.20% of Java online submissions for Non-decreasing Array.
     * Memory Usage: 43.3 MB, less than 97.06% of Java online submissions for Non-decreasing Array.
     * 최대 1개의 숫자를 바꿔서 non-decreasing array를 만들 수 있는지 파악후 결과를 반환한다
     */
    static class Solution1 {
        public boolean checkPossibility(int[] nums) {
            int count = 0, index = -1;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    count++;
                    index = i;
                }
            }

            if (count == 0) { // 이 문제의 포인트는 처음부터 엣지 케이스를 잘 생각할 수 있는지가 아닐까 생각된다.
                return true;
            } else if (count == 1) {
                if (index == 0 || index == nums.length - 2) {
                    return true;
                } else {
                    return nums[index + 1] >= nums[index - 1] || nums[index + 2] >= nums[index];
                }
            }
            return false;
        }
    }

    static class Solution2 {
        public boolean checkPossibility(int[] nums) {
            int numViolations = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > nums[i]) {

                    if (numViolations == 1) {
                        return false;
                    }

                    numViolations++;

                    if (i < 2 || nums[i - 2] <= nums[i]) {
                        nums[i - 1] = nums[i];
                    } else {
                        nums[i] = nums[i - 1];
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(s1.checkPossibility(new int[]{4, 2, 1}));
        System.out.println(s1.checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(s1.checkPossibility(new int[]{5, 7, 1, 8}));
    }
}
