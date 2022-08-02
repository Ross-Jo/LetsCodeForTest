package com.letscodefortest.medium.bit;

import java.util.*;

// https://leetcode.com/problems/subsets-ii

public class Leetcode_subsets_2_q90 {

    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    /**
     * time complexity: O(N*2^N)
     * space complexity: O(N*2^N)
     * Runtime: 10 ms, faster than 10.80% of Java online submissions for Subsets II.
     * Memory Usage: 44.5 MB, less than 24.17% of Java online submissions for Subsets II.
     * 비트 마스킹을 이용하여 subset을 구한다. subset의 경우 2^N 가지 그리고 각각의 경우마다 N개의 비트들을 보아 subset에 포함되는지 아닌지 알아내야 한다.
     */
    static class Solution1 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            nums = Arrays.stream(nums).sorted().toArray();
            Set<List<Integer>> bucket = new HashSet<>();

            for (int i = (1 << nums.length) - 1; i >= 0; i--) {
                List<Integer> target = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    int on = 1 << (nums.length - j - 1);
                    if ((on & i) == on) {
                        target.add(nums[j]);
                    }
                }
                bucket.add(target);
            }
            return new ArrayList<>(bucket);
        }
    }

    /**
     * time complexity: O(N*2^N)
     * space complexity: O(N*2^N)
     * Runtime: 11 ms, faster than 10.70% of Java online submissions for Subsets II.
     * Memory Usage: 46.1 MB, less than 5.16% of Java online submissions for Subsets II.
     * 비트 마스킹을 이용하여 subset을 구한다. subset의 경우 2^N 가지 그리고 각각의 경우마다 N개의 비트들을 보아 subset에 포함되는지 아닌지 알아내야 한다.
     */
    static class Solution2 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            int n = nums.length;

            // 중복 제거를 위해 필요
            Arrays.sort(nums);
            // 전체 집합
            int maxNumberOfSubsets = (int) Math.pow(2, n);

            Set<String> seen = new HashSet<>();

            for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
                List<Integer> currentSubset = new ArrayList<>();
                StringBuilder hashcode = new StringBuilder();

                for (int i = 0; i < n; i++) {
                    int mask = 1 << i;
                    int isSet = mask & subsetIndex;
                    if (isSet != 0) {
                        currentSubset.add(nums[i]);
                        hashcode.append(nums[i]).append(",");
                    }
                    // 그냥 List<Integer>를 구체클래스가 List<Integer>인 set에 넣어도,
                    // 순서만 맞다면 중복제거가 되지만 이렇게 하면...음..따로 속도 향상이 있지는 않고 비슷한 결과가 나온다.
                    // TODO: 내부적으로 Set<List<Integer>>가 어떻게 중복제거를 하는지 알 필요가 있음
                    if (!seen.contains(hashcode.toString())) {
                        seen.add(hashcode.toString());
                        subsets.add(currentSubset);
                    }
                }
            }
            return subsets;
        }
    }

    /**
     * time complexity: O(N*2^N)
     * space complexity: O(logN) - because of sorting, 소팅에 대한 공간복잡도를 고려했다는 점이 인상적
     * Runtime: 2 ms, faster than 85.93% of Java online submissions for Subsets II.
     * Memory Usage: 44.1 MB, less than 50.07% of Java online submissions for Subsets II.
     * 새로운 element가 추가 될 때마다 기존 집합에 새로운 element들을 더해 추가적인 set의 요소를 구해나가는 방식.단 element가 duplicate인
     * 경우 이전에 생성된 subset에 대하여만 새로운 element를 추가하여 추가적인 set요소를 구해 나간다.
     */
    static class Solution3 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());

            int subsetSize = 0;

            for (int i = 0; i < nums.length; i++) {
                int startingIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0; // 이전 주기 종료지점, subset을 만들어가기 위한
                // 시작 인덱스 이기도 함. duplicate인 요소가 있다면 생성된 subset에 대해서만 element를 추가하고, 그게 아니라면 모든 element에 대해서
                // duplicate elements를 추가함
                subsetSize = subsets.size(); // 아래 코드로 인해 새로 생성된 element의 갯수까지 반영한 총 size
                for (int j = startingIndex; j < subsetSize; j++) {
                    List<Integer> currentSubset = new ArrayList<>(subsets.get(j)); // 요소 복사 & 수정 - deep copy
                    currentSubset.add(nums[i]);
                    subsets.add(currentSubset);
                }
            }

            return subsets;
        }
    }

    /**
     * time complexity: O(N*2^N)
     * space complexity: O(N)
     * Runtime: 2 ms, faster than 85.93% of Java online submissions for Subsets II.
     * Memory Usage: 44.4 MB, less than 25.19% of Java online submissions for Subsets II.
     * 재귀적 방식
     */
    static class Solution4 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> subsets = new ArrayList<>();
            List<Integer> currentSubset = new ArrayList<>();

            subsetsWithDupHelper(subsets, currentSubset, nums, 0);
            return subsets;
        }

        private void subsetsWithDupHelper(List<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int index) {
            subsets.add(new ArrayList<>(currentSubset)); // deep copy

            for (int i = index; i < nums.length; i++) {
                if (i != index && nums[i] == nums[i - 1]) {
                    continue;
                }
                currentSubset.add(nums[i]);
                subsetsWithDupHelper(subsets, currentSubset, nums, i + 1);
                currentSubset.remove(currentSubset.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(s1.subsetsWithDup(new int[]{4, 4, 4, 1, 4}));
    }
}
