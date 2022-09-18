package com.letscodefortest.medium.dp;

// https://leetcode.com/problems/allocate-mailboxes/

import java.util.Arrays;

public class Leetcode_allocate_mailboxes_q1478 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N^3) = O(N^3), O(K*N*N)
     * space complexity: O(N^2)
     * Runtime: 13 ms, faster than 75.14% of Java online submissions for Allocate Mailboxes.
     * Memory Usage: 42.1 MB, less than 70.17% of Java online submissions for Allocate Mailboxes.
     * median이 mailbox를 놓을 수 있는 최적 위치라는 사실을 증명 후, dp를 적용한다.
     * 해설참조 : https://leetcode.com/problems/allocate-mailboxes/discuss/685620/JavaC%2B%2BPython-Top-down-DP-Prove-median-mailbox-O(n3)
     */
    static class Solution1 {
        public final int MAX = 100, INF = 100 * 10000;
        int[][] costs = new int[MAX][MAX];
        Integer[][] memo = new Integer[MAX][MAX];

        public int minDistance(int[] houses, int k) {
            int n = houses.length;
            Arrays.sort(houses); // 집의 위치를 나타내는 숫자를 정렬한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int medianPos = houses[(i + j) / 2]; // index를 통해 median위치를 잡는다. (집의 직접적인 위치가 아니라)
                    for (int m = i; m <= j; m++) {
                        costs[i][j] += Math.abs(medianPos - houses[m]);
                        // cost[i][j], Let costs[i][j] is the total travel distance from houses houses[i:j] to a mailbox when putting the mailbox among houses[i:j],
                        // the best way is put the mail box at median position among houses[i:j]
                    }
                }
            }
            return dp(n, k, 0);
        }

        int dp(int n, int k, int i) {
            if (k == 0 && i == n) return 0; // 올바른 case인 경우
            if (k == 0 || i == n) return INF; // 미리 mailbox가 모두 소진되어 버리거나, 모든 범위에 대한 계산을 마쳤으나, 메일박스가 추가로 소진되어 버린경우
            if (memo[k][i] != null) return memo[k][i];
            int ans = INF;
            for (int j = i; j < n; j++) {
                ans = Math.min(ans, costs[i][j] + dp(n, k - 1, j + 1)); // dp를 통해 최소값을 계산, for문을 통해 각 case별로 최소가 되는 값을 선택하도록 함
            }
            return memo[k][i] = ans;
        }
    }

    public static void main(String[] args) {
    }
}
