package com.letscodefortest.medium.rule;

import java.util.*;

// https://leetcode.com/problems/brick-wall/

public class Leetcode_brick_wall_q554 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(n)
     * space complexity: O(n)
     * Runtime: 13 ms, faster than 87.97% of Java online submissions for Brick Wall.
     * Memory Usage: 45.5 MB, less than 89.86% of Java online submissions for Brick Wall.
     * 틈이 있는 경우를 모두 파악해, 파괴 되어야 하는 벽돌의 최소 갯수를 구한다
     */
    static class Solution1 {
        public int leastBricks(List<List<Integer>> wall) {
            Map<Integer, Integer> counter = new HashMap<>();

            for (List<Integer> integers : wall) {
                if (integers.size() > 1) {
                    int sum = 0;
                    for (int j = 0; j < integers.size() - 1; j++) {
                        sum += integers.get(j);
                        int count = counter.getOrDefault(sum, 0);
                        counter.put(sum, count + 1);
                    }
                } else {
                    if (counter.getOrDefault(integers.get(0), null) == null) {
                        counter.put(integers.get(0), 0);
                    }
                }
            }
            return wall.size() - Collections.max(counter.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        }
    }

    // brute force
    static class Solution2 { // 뭔가 이 문제는 brute force가 더 주목할 만한 문제인것 같기도..
        public int leastBricks(List<List<Integer>> wall) {
            int[] pos = new int[wall.size()]; // i 번재 row의 brick index를 나타냄
            int sum = 0, res = Integer.MAX_VALUE;
            for (int el : wall.get(0)) {
                sum += el;
            }

            // width 1 짜리의 가상의 벽돌로 나누고, 칼럼마다 순회하면서 cutting을 일으키는지 아닌지 확인
            while (sum != 0) {
                int count = 0;
                for (int i = 0; i < wall.size(); i++) { // 칼럼방향 순회
                    List<Integer> row = wall.get(i);
                    if (row.get(pos[i]) != 0) count++;
                    else pos[i]++;
                    row.set(pos[i], row.get(pos[i]) - 1); // brick의 width를 업데이트 함으로서 바운더리를 쳤는지 안쳤는지를 판단하기 위함.
                    // 만약에 바운더리를 쳤다면 다음 loop때 i번째 row에서 다음 블록으로 넘어가게 될 것
                }
                sum--;
                res = Math.min(res, count);
            }
            return res;
        }
    }

    // better brute force
    static class Solution3 {
        public int leastBricks(List<List<Integer>> wall) {
            int[] pos = new int[wall.size()];
            int sum = 0, res = Integer.MAX_VALUE;
            for (int el : wall.get(0)) {
                sum += el;
            }

            while (sum != 0) {
                int count = 0, mini = Integer.MAX_VALUE;
                for (int i = 0; i < wall.size(); i++) {
                    List<Integer> row = wall.get(i);
                    if (row.get(pos[i]) != 0) count++;
                    else pos[i]++;
                    mini = Math.min(mini, row.get(pos[i])); // 컬럼 방향 순회를 할때, 마주치는 벽돌 중 제일 짦은 벽돌 기준으로 점프하기 위해 (한칸씩 점프하는게 아니라)
                }
                for (int i = 0; i < wall.size(); i++) {
                    List<Integer> row = wall.get(i);
                    row.set(pos[i], row.get(pos[i]) - mini);
                }
                sum -= mini;
                res = Math.min(res, count);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.leastBricks(List.of(List.of(1, 2, 2, 1), List.of(3, 1, 2), List.of(1, 3, 2), List.of(2, 4), List.of(3, 1, 2), List.of(1, 3, 1, 1))));
        System.out.println(s1.leastBricks(List.of(List.of(1), List.of(1), List.of(1))));
    }
}
