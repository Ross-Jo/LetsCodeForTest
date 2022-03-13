package com.letscodefortest.medium;

// https://leetcode.com/problems/container-with-most-water/

public class Leetcode_q11 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 5 ms, faster than 50.39% of Java online submissions for Container With Most Water.
     * Memory Usage: 81 MB, less than 28.41% of Java online submissions for Container With Most Water.
     * 바깥쪽에서 부터 왼쪽과 오른쪽 경계선을 좁혀들어오면서 최대 면적을 계산한다. 좁혀 들어올 때, 왼쪽과 오른쪽 경계벽 중 작은쪽을 좁히면서 양 벽이 형성하는 면적을 계산해 최대 면적을 갱신해준다
     * 이때 작은 높이를 가지는 쪽의 벽을 좁히는 이유는 width가 좁아지는것을 상쇄하면서도 동시에 maxArea의 상승이 가능한 방향이기 때문이다 (보다 높을 벽을 취함으로서, 수위가 높아지는 경우가 가능하기 때문)
     *
     */
    static class Solution1 {
        public int maxArea(int[] height) {
            int max = 0;
            int left = 0;
            int right = height.length - 1;

            while (left < right) {
                max = Math.max(max, (right - left) * Math.min(height[right], height[left]));
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return max;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.maxArea(new int[]{1,1}));
    }
}
