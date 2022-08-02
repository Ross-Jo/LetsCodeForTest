package com.letscodefortest.medium.bit;

// https://leetcode.com/problems/number-of-1-bits

public class Leetcode_number_of_1_bits_q191 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(1)
     * space complexity: O(1)
     * Runtime: 1 ms, faster than 85.79% of Java online submissions for Number of 1 Bits.
     * Memory Usage: 40.9 MB, less than 67.90% of Java online submissions for Number of 1 Bits.
     */
    static class Solution1 {
        public int hammingWeight(int n) {
            int target = 1, count = 0;
            for (int i = 0; i < 32; i++, target <<= 1) {
                if ((n & target) == target) {
                    count++;
                }
            }
            return count;
        }
    }

    // n의 least significant 1-bit가 n-1의  0-bit에 언제나 대응하기 때문에 해당성질을 이용해 비트 & 연산으로 하나씩 1을 삭제 & 카운트 해준다
    static class Solution2 {
        public int hammingWeight(int n) {
            int sum = 0;
            while (n != 0) {
                sum++;
                n &= (n - 1);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.hammingWeight(0));
    }
}
