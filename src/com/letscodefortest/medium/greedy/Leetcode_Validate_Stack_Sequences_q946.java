package com.letscodefortest.medium.greedy;

// https://leetcode.com/problems/validate-stack-sequences/

import java.util.Stack;

public class Leetcode_Validate_Stack_Sequences_q946 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 4 ms, faster than 66.83% of Java online submissions for Validate Stack Sequences.
     * Memory Usage: 45.5 MB, less than 26.92% of Java online submissions for Validate Stack Sequences.
     * pushed 데이터를 stack에 넣음과 동시에 popped 배열을 함께 봐줌으로서 이번에 들어간 number 및 다른 number들을 빼 줄 수 있는 상황이면 바로 빼준다.
     *
     */
    static class Solution1 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0, index = 0; i < pushed.length; i++) {
                stack.push(pushed[i]);
                while(!stack.isEmpty() && stack.peek() == popped[index]) {
                    stack.pop();
                    index++;
                }
            }

            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
//        System.out.println(s1.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(s1.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4,3,5,1,2}));
    }
}
