package com.letscodefortest.q12;

// https://leetcode.com/problems/sliding-window-maximum

import java.util.ArrayDeque;

public class Q12 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 41 ms, faster than 23.49% of Java online submissions for Sliding Window Maximum.
     * Memory Usage: 132.7 MB, less than 5.00% of Java online submissions for Sliding Window Maximum.
     * deque에 index를 넣어가면서 최대값을 유지하는 deque을 유지해, 항상 sliding window의 최대값을 산출해내는 방법
     * deque에 인자를 삽입할 시 초기화 과정 및, 인자 삽입중 deque의 왼쪽에 위치하고 있는 삽입하려는 인자보다 작은 인자들을 deque에서 제거하는게 주요한 포인트.
     */
    static class Solution1 {
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        int[] nums;

        public void clean_deque(int i, int k) {
            if (!deq.isEmpty() && deq.getFirst() == i - k) deq.removeFirst();
            while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) deq.removeLast();
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) return new int[0];
            if (k == 1) return nums;

            this.nums = nums;
            int max_idx = 0;
            for (int i = 0; i < k; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                if (nums[i] > nums[max_idx]) max_idx = i;
            }
            int[] output = new int[n - k + 1];
            output[0] = nums[max_idx];

            for (int i = k; i < n; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                output[i - k + 1] = nums[deq.getFirst()];
            }
            return output;
        }
    }

    public static void main(String[] args) {

    }
}
