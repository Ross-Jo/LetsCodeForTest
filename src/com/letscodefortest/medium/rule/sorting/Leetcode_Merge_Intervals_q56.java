package com.letscodefortest.medium.rule.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// https://leetcode.com/problems/merge-intervals

public class Leetcode_Merge_Intervals_q56 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(NlogN)
     * space complexity: O(N)
     * Runtime: 10 ms, faster than 74.60% of Java online submissions for Merge Intervals.
     * Memory Usage: 47.5 MB, less than 79.80% of Java online submissions for Merge Intervals.
     * interval의 시작지점을 기준으로 오름차순 sorting 한 다음, 차례로 merge가 가능한 interval들에 대해서는 merge를 해주고, merge가 불가능한 케이스일 경우
     * answer set에 추가 시켜주는 과정을 모든 interval들에 대하여 행해준다 - 해설참조.
     * TODO: O(N) solution은 없는걸까?
     */
    static class Solution1 {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // interval의 시작지점기준 오름차순 정렬
            LinkedList<int[]> merged = new LinkedList<>();

            for (int[] interval : intervals) {
                if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                    merged.add(interval); // 만약 merge가 불가능한 경우라면 정답 셋에 추가
                } else {
                    merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]); // merge가 가능한 경우라면 interval 끼리 merge
                }
            }
            return merged.toArray(new int[merged.size()][]);
        }
    }

    public static void main(String[] args) {

    }
}
