package com.letscodefortest.medium;

// https://leetcode.com/problems/coin-change/

/**
 * time complexity: O(N)
 * space complexity: O(N)
 * Runtime: 13 ms, faster than 62.34% of Java online submissions for Coin Change.
 * Memory Usage: 38.6 MB, less than 52.11% of Java online submissions for Coin Change.
 * 0원부터 목표 금액까지 나아가면서 해당 금액을 "낼 수 있는 최소 화폐 갯수"를 순차적으로 계산해가면서 dp 테이블을 채워준다.
 */
import java.util.Arrays;

public class Leetcode_q322 {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        Arrays.fill(dp, -1); // 낼 수 없는 경우를 표현하기 위함

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                dp[coins[i]] = 1; // 단위 금액은 1로 초기화
            }
        }
        dp[0] = 0; // 0개 내면 낼 수 있는 금액

        for (int i = 1; i <= amount; i++) {
            if (dp[i] == -1) { // 단위 금액이 아닌 경우
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) { // 단위 금액 만큼 뺄 수 있을때
                        int a = dp[i - coins[j]];
                        int b = dp[coins[j]];
                        if (a != -1 && b != -1) { // 둘 다 낼 수 있는 금액이어야 함
                            min = Math.min(min, a + b);
                        }
                    }
                }
                dp[i] = min != Integer.MAX_VALUE ? min : -1;
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1}, 2));
    }
}
