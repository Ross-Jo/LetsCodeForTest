package com.letscodefortest.q8;

import java.util.Arrays;

public class Q8 {
    static Solution1 solution1 = new Solution1();
    static Solution2 solution2 = new Solution2();
    static Solution3 solution3 = new Solution3();

    static class Solution1 { // time out
        static int[] note;

        public int maxProfit(int[] prices) {
            note = new int[prices.length+1];
            Arrays.fill(note, -1);

            return getMax(prices,0,0);
        }

        public int getMax(int[] prices, int start, int depth) {
            if (start >= prices.length-1 || depth > 1) return 0;

            int maxProfit = 0;
            for (int i=start; i<prices.length; i++) {
                for (int j=i+1; j<prices.length; j++) {
                    if (prices[i] < prices[j]) {
                        int profit = prices[j] - prices[i];
                        if(depth == 0) {
                            if (note[j+1] == -1) {
                                profit += getMax(prices, j+1, depth + 1);
                            } else {
                                profit += note[j+1];
                            }
                        }
                        maxProfit = Math.max(maxProfit, profit);
                    }
                }
                note[start] = maxProfit;
            }

            return maxProfit;
        }
    }

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 10 ms, faster than 22.09% of Java online submissions for Best Time to Buy and Sell Stock III.
     * Memory Usage: 53.6 MB, less than 70.30% of Java online submissions for Best Time to Buy and Sell Stock III.
     * 구간을 2개로 나눠서 각 구간이 최대 1번 거래한다고 가정하고 구간별 maximum profit을 구해서 합산한다.
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int length = prices.length;
            if (length <= 1) return 0;

            int leftMin = prices[0];
            int rightMax = prices[length - 1];

            int[] leftProfits = new int[length];
            int[] rightProfits = new int[length + 1];

            for (int l = 1; l < length; l++) {
                leftProfits[l] = Math.max(leftProfits[l-1], prices[l] - leftMin);
                leftMin = Math.min(leftMin, prices[l]);

                int r = length - 1 - l;
                rightProfits[r] = Math.max(rightProfits[r+1], rightMax - prices[r]);
                rightMax = Math.max(rightMax, prices[r]);
            }

            int maxProfit = 0;
            for (int i = 0; i < length; i++) {
                maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i+1]);
            }
            return maxProfit;
        }
    }

    /**
     * time complexity: O(N)
     * space complexity: O(1)
     * Runtime: 848 ms, faster than 5.06% of Java online submissions for Best Time to Buy and Sell Stock III.
     * Memory Usage: 48 MB, less than 98.56% of Java online submissions for Best Time to Buy and Sell Stock III.
     * 각 price point 마다 1번 거래의 잠재 profit을 구하고 그 profit 만큼 cost에서 제해서 2번째 거래의 profit maximum을 구한다
     */
    static class Solution3 {
        public int maxProfit(int[] prices) {
            int t1Cost = Integer.MAX_VALUE, t2Cost = Integer.MAX_VALUE;
            int t1Profit = 0, t2Profit = 0;

            for (int price : prices) {

                t1Cost = Math.min(t1Cost, price);
                t1Profit = Math.max(t1Profit, price - t1Cost);

                t2Cost = Math.min(t2Cost, price - t1Profit);
                t2Profit = Math.max(t2Profit, price - t2Cost);

                System.out.println();
            }

            return t2Profit;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution3.maxProfit(new int[]{1,2,1,10,6,9}));
    }
}
