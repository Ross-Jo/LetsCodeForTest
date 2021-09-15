package com.letscodefortest.medium;

public class Leetcode_q33 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(logN)
     * space complexity: O(1)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 38.4 MB, less than 64.81% of Java online submissions for Search in Rotated Sorted Array.
     * rotate된 증가 수열에서 이분탐색의 탐색 방향을 case에 따라 달리하여 진행한다.
     */
    static class Solution1 { // solution 참고
        public int search(int[] nums, int target) {
            int start = 0, end = nums.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] >= nums[start]) { // 만약 mid가 start 지점부터 계속 증가하는 수열의 부분에 위치하고 있고
                    if (target >= nums[start] && target < nums[mid]) end = mid - 1; // 타깃이 그 사이에 위치 하는 경우, 왼쪽 탐색
                    else start = mid + 1; // 아니면 오른쪽 탐색
                } else { // 만약 mid가 start~mid 지점중 끊기는 지점이 있는 경우
                    if (target <= nums[end] && target > nums[mid]) start = mid + 1; // target이 오른쪽의 증가수열에 위치한다면 오른쪽으로 탐색
                    else end = mid - 1; // 그렇지 않으면 왼쪽으로 탐색
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(s1.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(s1.search(new int[]{1}, 0));
        System.out.println(s1.search(new int[]{1, 3}, 2));
        System.out.println(s1.search(new int[]{8, 9, 0, 1, 3, 4, 6}, 7));
    }
}
