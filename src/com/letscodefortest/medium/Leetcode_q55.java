package com.letscodefortest.medium;

// https://leetcode.com/problems/jump-game/

public class Leetcode_q55 {
    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 1 ms, faster than 87.40% of Java online submissions for Jump Game.
     * Memory Usage: 39.9 MB, less than 88.97% of Java online submissions for Jump Game.
     * 음수가 아닌 element를 가지는 배열이기 때문에 배열중 0인 값에만 주목하면 되며, 해당 0 값을 돌파 하는지 안하는지만 관찰하면 된다.
     */
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;

        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i]==0) {
                if (i == 0) return false;
                else {
                    int j = i - 1;
                    while (j >= 0) { // 0인 element에 수렴하는 구간이 있으면 안된다
                        if (i == nums.length-1) {
                            if (j + nums[j] >= i) break; // 마지막 요소가 0이면 해당 0에 수렴해도 된다.
                        } else {
                            if (j + nums[j] > i) break; // 하지만 마지막 요소가 0이 아닌 경우는 거꾸로 찾았을때 해당 0구간
                        }
                        j--;
                    }
                    if (j < 0) return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,0,0}));
    }
}
