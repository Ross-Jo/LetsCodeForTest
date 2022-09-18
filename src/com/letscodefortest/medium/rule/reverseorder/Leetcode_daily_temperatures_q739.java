package com.letscodefortest.medium.rule.reverseorder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.com/problems/daily-temperatures/
// 이번 문제는 다시 풀어볼 것. 과연 아래 풀이 방법들을 생각할 수 있는가? - 시간, 공간 복잡도 구하는것도 유의해서 볼 것
public class Leetcode_daily_temperatures_q739 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 50 ms, faster than 77.34% of Java online submissions for Daily Temperatures.
     * Memory Usage: 128.3 MB, less than 70.09% of Java online submissions for Daily Temperatures.
     * 온도를 순회할 때, monotonic stack을 이용하여 높은온도(stack bottom) 부터 낮은 온도(stack top)로 순서대로 적층한 다음(이때 index를 적층),
     * 최상단의 온도보다 높은 온도가 나왔을 경우, 스택의 요소들을 상단부터 차례로 pop하며 index간의 차이를 이용해 answer 배열을 채워준다.
     * pop은 최상단의 온도보다 높은 온도가 스택의 top이 될떄까지 진행한다.
     */
    static class Solution1 {
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] answer = new int[n];
            Deque<Integer> stack = new ArrayDeque<>();

            for (int currDay = 0; currDay < n; currDay++) {
                int currentTemp = temperatures[currDay];

                while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                    int prevDay = stack.pop();
                    answer[prevDay] = currDay - prevDay;
                }
                stack.push(currDay);

            }
            return answer;
        }
    }

    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 12 ms, faster than 94.60% of Java online submissions for Daily Temperatures.
     * Memory Usage: 114.4 MB, less than 85.33% of Java online submissions for Daily Temperatures.
     * 뒤에서부터 거꾸로 거슬러 올라오면서 현재 지점보다 온도가 더 높은 지점을 반복적으로 찾아가는데, 기존에 계산된 결과를 활용해서 찾아간다.
     */
    static class Solution2 {
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int hottest = 0;
            int answer[] = new int[n];

            for (int currDay = n - 1; currDay >= 0; currDay--) {
                int currentTemp = temperatures[currDay];
                if (currentTemp >= hottest) {
                    hottest = currentTemp;
                    continue;
                }

                int days = 1;

                while (temperatures[currDay + days] <= currentTemp) {
                    days += answer[currDay + days];
                }

                answer[currDay] = days;
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(s1.dailyTemperatures(new int[]{75, 71, 69, 72})));
    }
}
