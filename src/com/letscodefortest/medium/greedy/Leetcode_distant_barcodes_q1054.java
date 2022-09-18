package com.letscodefortest.medium.greedy;

import java.util.*;

public class Leetcode_distant_barcodes_q1054 {
    static Solution1 s1 = new Solution1();

    /**
     * https://leetcode.com/problems/distant-barcodes/
     * time complexity: O(NlogN + N * logN)
     * space complexity: O(2 * N)
     * Runtime: 143 ms, faster than 10.47% of Java online submissions for Distant Barcodes.
     * Memory Usage: 72.1 MB, less than 63.51% of Java online submissions for Distant Barcodes.
     * 최대 빈도를 가지는 숫자를 각 반복주기마다 꺼내어 기록한다. 단, 최대빈도 숫자와 마지막 기록한 숫자가 겹치는 경우 다음 최대빈도 숫자를 사용한다.
     */
    static class Solution1 {
        PriorityQueue<Map.Entry<Integer, Integer>> heap;
        List<Integer> result;

        public int[] rearrangeBarcodes(int[] barcodes) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int e : barcodes) {
                count.put(e, count.getOrDefault(e, 0) + 1);
            }

            heap = new PriorityQueue(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            for (Map.Entry<Integer, Integer> e : count.entrySet()) {
                heap.add(e);
            }

            result = new ArrayList<>();

            for (int i = 0; i < barcodes.length; i++) {
                result.add(getMostFrequentKey());
            }

            return result.stream().mapToInt(i -> i).toArray();
        }

        int getMostFrequentKey() {
            Map.Entry<Integer, Integer> entry = heap.poll();
            if (entry.getValue() == 0) return -1;

            int mostFrequentKey;
            if (result.size() == 0 || (int) result.get(result.size() - 1) != (int) entry.getKey()) {
                mostFrequentKey = entry.getKey();
                entry.setValue(entry.getValue() - 1);
            } else {
                mostFrequentKey = getMostFrequentKey(); // 다음 최고 빈도 element가 마지막에 추가된 element와 동일한 경우 이와 같이 재귀 로직을 호출한게 포인트
            }
            heap.add(entry);

            return mostFrequentKey;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(s1.rearrangeBarcodes(new int[]{})));
    }
}
