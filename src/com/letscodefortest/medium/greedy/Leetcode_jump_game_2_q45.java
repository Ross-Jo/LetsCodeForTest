package com.letscodefortest.medium.greedy;

// https://leetcode.com/problems/jump-game-ii/

public class Leetcode_jump_game_2_q45 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 2 ms, faster than 85.19% of Java online submissions for Jump Game II.
     * Memory Usage: 49.6 MB, less than 40.61% of Java online submissions for Jump Game II.
     * Greedy 하게 최소 점프 횟수를 구하되, 단계별 점프를 통해 갈 수 있는 구간 들을 나누고, currMax에 도달하면 count를
     * 증가시키는 한편, 다음 최대 점프가능 구간을 식별한다(nextMax)
     */
    static class Solution1 { // 해설참고 : https://bcp0109.tistory.com/280
        public int jump(int[] nums) {
            int count = 0, currMax = 0, nextMax = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                nextMax = Math.max(nextMax, i + nums[i]);

                if (i == currMax) {
                    count++;
                    currMax = nextMax;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.jump(new int[]{2, 3, 0, 1, 4}));
    }
}
