package com.letscodefortest.medium;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_restore_ip_addresses_q93 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(3^N)
     * space complexity: O(N)
     * Runtime: 9 ms, faster than 40.79% of Java online submissions for Restore IP Addresses.
     * Memory Usage: 43.1 MB, less than 55.21% of Java online submissions for Restore IP Addresses.
     * IP 판별 규칙에 맞게 재귀호출한다
     */
    static class Solution1 {

        List<String> bucket = new ArrayList<>();
        String s;

        public List<String> restoreIpAddresses(String s) {
            this.s = s;
            getIP(0, 0, "");
            return bucket;
        }

        public void getIP(int depth, int index, String ips) {
            if (depth == 4) {
                if (index == s.length()) {
                    bucket.add(ips.substring(1));
                }
                return;
            }

            String ip;

            if (index < s.length()) {
                ip = s.substring(index, index + 1);
                getIP(depth + 1, index + 1, ips + "." + ip);
            }

            if (index + 1 < s.length()) {
                ip = s.substring(index, index + 2);
                if (ip.startsWith("0")) {
                    return;
                }
                getIP(depth + 1, index + 2, ips + "." + ip);
            }

            if (index + 2 < s.length()) {
                ip = s.substring(index, index + 3);
                if (ip.startsWith("0") || Integer.parseInt(ip) > 255) {
                    return;
                }
                getIP(depth + 1, index + 3, ips + "." + ip);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.restoreIpAddresses("19216811"));
    }
}
