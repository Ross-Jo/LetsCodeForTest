package com.letscodefortest.medium.rule.reverseorder;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/

public class Leetcode_roman_to_integer_q13 {
    static Solution1 s1 = new Solution1();

    static class Solution1 {
        public int romanToInt(String s) {
            Map<Character, Integer> indicator = new HashMap<>();
            indicator.put('I', 1);
            indicator.put('V', 5);
            indicator.put('X', 10);
            indicator.put('L', 50);
            indicator.put('C', 100);
            indicator.put('D', 500);
            indicator.put('M', 1000);

            int sum = 0, n = s.length();
            for (int i = 0; i < n; ) {
                if (i != n - 1) {
                    if (s.charAt(i) == 'I') {
                        if (s.charAt(i + 1) == 'V') {
                            sum += 4;
                            i = i + 2;
                            continue;
                        } else if (s.charAt(i + 1) == 'X') {
                            sum += 9;
                            i = i + 2;
                            continue;
                        }
                    } else if (s.charAt(i) == 'X') {
                        if (s.charAt(i + 1) == 'L') {
                            sum += 40;
                            i = i + 2;
                            continue;
                        } else if (s.charAt(i + 1) == 'C') {
                            sum += 90;
                            i = i + 2;
                            continue;
                        }
                    } else if (s.charAt(i) == 'C') {
                        if (s.charAt(i + 1) == 'D') {
                            sum += 400;
                            i = i + 2;
                            continue;
                        } else if (s.charAt(i + 1) == 'M') {
                            sum += 900;
                            i = i + 2;
                            continue;
                        }
                    }
                }
                sum += indicator.get(s.charAt(i++));
            }
            return sum;
        }
    }

    static class Solution2 {
        static Map<String, Integer> values = new HashMap<>();

        static {
            values.put("M", 1000);
            values.put("D", 500);
            values.put("C", 100);
            values.put("L", 50);
            values.put("X", 10);
            values.put("V", 5);
            values.put("I", 1);
        }

        public int romanToInt(String s) {
            int sum = 0;
            int i = 0;

            while (i < s.length()) {
                String currentSymbol = s.substring(i, i + 1);
                int currentValue = values.get(currentSymbol);
                int nextValue = 0;

                if (i + 1 < s.length()) {
                    String nextSymbol = s.substring(i + 1, i + 2);
                    nextValue = values.get(nextSymbol);
                }
                if (currentValue < nextValue) {
                    sum += (nextValue - currentValue);
                    i += 2;
                } else {
                    sum += currentValue;
                    i += 1;
                }
            }

            return sum;
        }

    }

    static class Solution3 {
        static Map<String, Integer> values = new HashMap<>();

        static {
            values.put("I", 1);
            values.put("V", 5);
            values.put("X", 10);
            values.put("L", 50);
            values.put("C", 100);
            values.put("D", 500);
            values.put("M", 1000);
            values.put("IV", 4);
            values.put("IX", 9);
            values.put("XL", 40);
            values.put("XC", 90);
            values.put("CD", 400);
            values.put("CM", 900);
        }

        public int romanToInt(String s) {
            int sum = 0;
            int i = 0;

            while (i < s.length()) {
                if (i < s.length() - 1) {
                    String doubleSymbol = s.substring(i, i + 2);
                    // Check if this is the length-2 symbol case.
                    if (values.containsKey(doubleSymbol)) {
                        sum += values.get(doubleSymbol);
                        i += 2;
                        continue;
                    }
                }
                // Otherwise, it must be the length-1 symbol case.
                String singleSymbol = s.substring(i, i + 1);
                sum += values.get(singleSymbol);
                i += 1;
            }
            return sum;
        }
    }

    static class Solution4 {
        static Map<String, Integer> values = new HashMap<>();

        static {
            values.put("M", 1000);
            values.put("D", 500);
            values.put("C", 100);
            values.put("L", 50);
            values.put("X", 10);
            values.put("V", 5);
            values.put("I", 1);
        }

        public int romanToInt(String s) {
            String lastSymbol = s.substring(s.length() - 1);
            int lastValue = values.get(lastSymbol);
            int total = lastValue;

            for (int i = s.length() - 2; i >= 0; i--) {
                String currentSymbol = s.substring(i, i + 1);
                int currentValue = values.get(currentSymbol);
                if (currentValue < lastValue) {
                    total -= currentValue;
                } else {
                    total += currentValue;
                }
                lastValue = currentValue;
            }
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.romanToInt("III"));
        System.out.println(s1.romanToInt("LVIII"));
        System.out.println(s1.romanToInt("MCMXCIV"));
    }
}
