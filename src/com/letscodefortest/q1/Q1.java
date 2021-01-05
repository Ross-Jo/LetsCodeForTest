package com.letscodefortest.q1;

import java.util.Stack;

public class Q1 {

    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for (Character c: s.toCharArray()) {
            if ('0'<=c && c<='9') {
                stack.push(c.toString());
            } else if (c == '[') {
                stack.push(c.toString());
            } else if (c == ']') {
                StringBuilder tmp_builder1 = new StringBuilder();
                while(!stack.isEmpty() && stack.peek().charAt(0)!='[') {
                    tmp_builder1.insert(0, stack.pop());
                }
                stack.pop();
                int cnt = 0, k = 0;
                while(!stack.isEmpty() && stack.peek().length() == 1 && '0'<=stack.peek().charAt(0) && stack.peek().charAt(0)<='9') {
                    k += Integer.parseInt(stack.pop()) * Math.pow(10, cnt++);
                }
                StringBuilder tmp_builder2 = new StringBuilder();
                for (int i=0; i<k; i++) {
                    tmp_builder2.append(tmp_builder1.toString());
                }
                stack.push(tmp_builder2.toString());
            } else {
                stack.push(c.toString());
            }
        }

        StringBuilder tmp = new StringBuilder();
        while(!stack.isEmpty()) {
            tmp.insert(0, stack.pop());
        }

        return result.append(tmp).toString();
    }

    public String decodeString_solution(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<StringBuilder> stringBuilderStack = new Stack<>();
        Stack<Integer> integerStack = new Stack<>();

        for (int i=0; i<s.length(); i++) {

            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                if (!integerStack.isEmpty() && Character.isDigit(s.charAt(i-1))) {
                    integerStack.push(integerStack.pop() * 10 + (c - '0'));
                } else {
                    integerStack.push(c - '0');
                }
            } else if (c == '[') {

                stringBuilderStack.push(stringBuilder);
                stringBuilder = new StringBuilder();

            } else if (c == ']') {

                int count = integerStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j=0; j<count; j++) tmp.append(stringBuilder);
                stringBuilder = stringBuilderStack.pop().append(tmp);

            } else {

                stringBuilder.append(c);

            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        System.out.println(q1.decodeString_solution("2[abc3[a]]"));
    }
}
