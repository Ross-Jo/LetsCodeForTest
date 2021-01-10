package com.letscodefortest.q4;

import java.util.Arrays;
import java.util.Stack;

// question : https://leetcode.com/problems/trapping-rain-water/solution/
// solution 참고 : https://medium.com/leetnotes/leetcode-42-trapping-rain-water-b8e325e72167

public class Q4 {

    public int trap_approach0 (int[] height) { // timeout
        if (height.length == 0) return 0;
        int ans = 0, max = Arrays.stream(height).max().getAsInt();

        for (int i=1; i<=max; i++) {
            int[] leftAndRight = findLeftAndRight(height, i);
            ans += calcWater(leftAndRight, height, i);
        }
        return ans;
    }

    private int calcWater(int[] leftAndRight, int[] height, int level) {
        int left = leftAndRight[0];
        int right = leftAndRight[1];

        int cnt = 0;
        for (int i=left; i<=right; i++) {
            if (height[i] < level) cnt++;
        }
        return cnt;
    }

    private int[] findLeftAndRight(int[] height, int level) {
        int left = -1;
        int right = height.length;

        for (int i=0; i<height.length; i++) {
            if (level <= height[i]) {
                left = i;
                break;
            }
        }

        for (int i=height.length-1; i>=0; i--) {
            if (level <= height[i]) {
                right = i;
                break;
            }
        }

        return new int[]{left, right};
    }

    /**
     * solution1 : brute force
     * time complexity : O(n^2) : 69ms
     * space complexity : O(1)
     * 인덱스 i 를 기준으로 왼쪽 및 오른쪽 벽을 찾고 물의 높이를 한정
     */
    public int trap_approach1 (int[] height) {
        int res = 0;
        for (int i=0; i<height.length; i++) {
            int h = height[i];
            int left = 0, right = 0;
            for (int j=i; j>=0; j--) left = Math.max(left, height[j]);
            for (int j=i; j<height.length; j++) right = Math.max(right, height[j]);
            if (Math.min(left, right) > h) res += (Math.min(left, right) - h);
        }
        return res;
    }

    /**
     * solution2 : dynamic programming
     * time complexity : O(n) : 1ms
     * space complexity : O(n)
     * 앞선 아이디어에서 왼쪽 및 오른쪽 벽을 매번 탐색하는 작업을 최초 서칭시 배열에 기록함으로서 1번에 끝냄
     */
    public int trap_approach2 (int[] height) {
        int res = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i=1; i<height.length; i++) max_left[i] = Math.max(height[i-1], max_left[i-1]);
        for (int i=height.length-2; i>-1; i--) max_right[i] = Math.max(height[i+1], max_right[i+1]);

        for (int i=1; i<height.length; i++) {
            int h = height[i];
            int left = max_left[i];
            int right = max_right[i];
            if (Math.min(left, right) > h) res += Math.min(left, right) - h;
        }
        return res;
    }

    /**
     * solution3 : two pointers
     * time complexity : O(n) : 1ms
     * space complexity : O(1)
     * 2개의 포인터를 이용해서 양쪽에서 내부로 좁혀들어오면서, 물의 높이가 한정될 수 있는 지점에서 계속적으로 쌓일 수 있는 물량을 계산, 정답 도출
     */
    public int trap_approach3 (int[] height) {
        int res = 0;
        int max_left = 0, max_right = 0;
        int left = 1, right = height.length - 2;

        while (left <= right) {
            max_left = Math.max(max_left, height[left-1]);
            max_right = Math.max(max_right, height[right+1]);

            if (max_left <= max_right) { // 물의 높이가 확정된 지점에서, 물량 계산이 가능함
                if (max_left > height[left]) res += max_left - height[left];
                left += 1;
            } else {
                if (max_right > height[right]) res += max_right - height[right];
                right -= 1;
            }
        }
        return res;
    }

    /**
     * solution4 : using stack
     * time complexity : O(n) : 3ms
     * space complexity : O(n)
     * 인덱스를 저장하는 스택을 이용해 스택에 들어가 있는 인덱스가 가지는 높이보다 높은 높이를 가지는 인덱스가 삽입되는 경우
     * 쌓일 수 있는 물의 양을 한정하고, 계산하는 방식을 반복해 정답 도출
     */
    public int trap_approach4 (int[] height) {
        int res = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        while(current < height.length) {
            while(!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                res += distance * bounded_height;
            }
            stack.push(current++);
        }
        return res;
    }

    public static void main(String[] args) {
        Q4 q4 = new Q4();
        System.out.println(q4.trap_approach4(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
