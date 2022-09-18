package com.letscodefortest.easy;

import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/

public class Leetcode_valid_parentheses_q20 {
    static Solution1 s1 = new Solution1();

    static class Solution1 {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();

            char[] input = s.toCharArray();
            for (char c : input) {
                if (c == '[' || c == '{' || c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    char top = stack.peek();
                    switch (c) {
                        case ']':
                            if (top == '[') {
                                stack.pop();
                            } else {
                                return false;
                            }
                            break;
                        case '}':
                            if (top == '{') {
                                stack.pop();
                            } else {
                                return false;
                            }
                            break;
                        case ')':
                            if (top == '(') {
                                stack.pop();
                            } else {
                                return false;
                            }
                            break;
                    }
                }
            }

            return stack.empty();
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.isValid("(])"));
    }

}
