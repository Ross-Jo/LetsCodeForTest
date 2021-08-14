package com.letscodefortest.medium;

import java.util.*;

public class LeetCode_q347 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();
    static Solution3 s3 = new Solution3();

    /**
     * time complexity: O(NlogN)
     * space complexity: O(N)
     * Runtime: 14 ms, faster than 16.14% of Java online submissions for Top K Frequent Elements.
     * Memory Usage: 41.7 MB, less than 53.90% of Java online submissions for Top K Frequent Elements.
     * 각 숫자들에 대한 counting 및 sort
     */
    static class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int e : nums) {
                map.put(e, map.getOrDefault(e, 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
            entryList.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            return entryList.subList(0, k).stream().mapToInt(Map.Entry::getKey).toArray();
        }
    }

    /**
     * time complexity: O(NlogK)
     * space complexity: O(N+K)
     * Runtime: 15 ms, faster than 14.39% of Java online submissions for Top K Frequent Elements.
     * Memory Usage: 47.5 MB, less than 5.66% of Java online submissions for Top K Frequent Elements.
     * 각 숫자들에 대한 counting 및 우선순위 큐를 이용한 sort
     */
    static class Solution2 { // solution 참고
        public int[] topKFrequent(int[] nums, int k) {
            if (k == nums.length) {
                return nums;
            }

            Map<Integer, Integer> count = new HashMap<>();
            for (int n : nums) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(count::get));

            for (int n : count.keySet()) {
                heap.add(n);
                if (heap.size() > k) heap.poll();
            }

            int[] top = new int[k];
            for (int i = k - 1; i >= 0; --i) {
                top[i] = heap.poll();
            }

            return top;
        }
    }

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 13 ms, faster than 18.60% of Java online submissions for Top K Frequent Elements.
     * Memory Usage: 47.6 MB, less than 5.66% of Java online submissions for Top K Frequent Elements.
     * 퀵소트 및 Lomuto의 파티션 알고리즘을 결합한 quick selection 솔루션
     */
    static class Solution3 { // Solution 참고
        int[] unique;
        Map<Integer, Integer> count;

        public void swap(int a, int b) {
            int tmp = unique[a];
            unique[a] = unique[b];
            unique[b] = tmp;
        }

        public int partition(int left, int right, int pivot_index) {
            int pivot_frequency = count.get(unique[pivot_index]);
            swap(pivot_index, right); // 피벗을 오른쪽 끝으로 보낸다
            int store_index = left; // 정렬을 위해 왼쪽 끝부터 시작한다

            for (int i = left; i <= right ; i++) { // move all less frequent elements to the left
                if (count.get(unique[i]) < pivot_frequency) {
                    swap(store_index, i);
                    store_index++; // frequency 경계값 이동의 의미, 해당 값을 이용해 다음 swap 가능 지점을 잡아준다.
                }
            }

            swap(store_index, right); // 최종 swap을 통해 파티션의 위치를 정위치 시칸다. (move pivot to its final place)

            return store_index;
        }

        public void quickSelect(int left, int right, int k_smallest) {
            // Sort a list within left..right till kth less frequent element takes its place.
            if (left == right) return;

            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left); // 랜덤 파티션 추출을 위한 행위 (재귀적이라 이와 같이 추출함)

            pivot_index = partition(left, right, pivot_index); // 정위치 시킨 인덱스를 재할당

            if (k_smallest == pivot_index) { // 피벗이 최종적으로 목표하는 위치에 위치한 경우
                return;
            } else if (k_smallest < pivot_index) {
                quickSelect(left, pivot_index - 1, k_smallest);
            } else {
                quickSelect(pivot_index + 1, right, k_smallest);
            }
        }

        public int[] topKFrequent(int[] nums, int k) {
            count = new HashMap<>();
            for (int num: nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            int n = count.size();
            unique = new int[n];
            int i = 0;
            for (int num: count.keySet()) {
                unique[i++] = num;
            }

            // kth top frequent element is (n - k)th less frequent.
            // Do a partial sort: from less frequent to the most frequent, till
            // (n - k)th less frequent element takes its place (n - k) in a sorted array.
            // All element on the left are less frequent.
            // All the elements on the right are more frequent.
            quickSelect(0, n-1, n-k);
            return Arrays.copyOfRange(unique, n - k, n);
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(s1.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}
