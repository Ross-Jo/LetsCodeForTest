package com.letscodefortest.medium.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/

public class Leetcode_restore_ip_addresses_q93 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

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

    static class Solution2 {
        int n;
        String s;
        LinkedList<String> segments = new LinkedList<>();
        ArrayList<String> output = new ArrayList<>();

        public boolean valid(String segment) {
            int m = segment.length();
            if (m > 3) return false;
            return (segment.charAt(0) != '0') ? (Integer.parseInt(segment) <= 255) : (m == 1);
        }

        public void update_output(int curr_pos) {
            String segment = s.substring(curr_pos + 1, n);
            if (valid(segment)) {
                segments.add(segment);
                output.add(String.join(".", segments));
                segments.removeLast();
            }
        }

        public void backtrack(int prev_pos, int dots) {
            int max_pos = Math.min(n - 1, prev_pos + 4);
            for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
                String segment = s.substring(prev_pos + 1, curr_pos + 1);
                if (valid(segment)) {
                    segments.add(segment);
                    if (dots - 1 == 0) update_output(curr_pos);
                    else backtrack(curr_pos, dots - 1);
                    segments.removeLast();
                }
            }
        }

        public List<String> restoreIpAddresses(String s) {
            n = s.length();
            this.s = s;
            backtrack(-1, 3);
            return output;
        }
    }

    public static void main(String[] args) {
        System.out.println(s1.restoreIpAddresses("19216811"));
    }
}
