package com.letscodefortest.medium.linkedlist;

// https://leetcode.com/problems/add-two-numbers/

public class Leetcode_add_two_numbers_q2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * Runtime: 2 ms, faster than 99.36% of Java online submissions for Add Two Numbers.
     * Memory Usage: 42.6 MB, less than 86.42% of Java online submissions for Add Two Numbers.
     * 링크드 리스트로 표현된 순자의 합계 구현
     */
    static class Solution1 {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int l1val = l1.val;
            int l2val = l2.val;
            int sum = l1val + l2val;

            int q = (sum) / 10;
            int r = (sum) % 10;

            return new ListNode(r, runner(q, l1.next, l2.next));
        }

        private ListNode runner(int quotient, ListNode l1, ListNode l2) {
            int l1val = 0, l2val = 0;
            if (l1 != null) {
                l1val = l1.val;
            }
            if (l2 != null) {
                l2val = l2.val;
            }
            int sum = l1val + l2val + quotient;

            int q = sum / 10;
            int r = sum % 10;

            if (l1 != null && l2 != null) {
                return new ListNode(r, runner(q, l1.next, l2.next));
            } else if (l1 == null && l2 != null) {
                return new ListNode(r, runner(q, null, l2.next));
            } else if (l1 != null && l2 == null) {
                return new ListNode(r, runner(q, l1.next, null));
            } else {
                if (r > 0 && q == 0) {
                    return new ListNode(r, runner(q, null, null));
                } else {
                    return null;
                }
            }
        }
    }

    static class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0); // 더미 헤드를 뒀다는 점이 포인트
            ListNode curr = dummyHead;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) { // 반복문을 다음과 같이 수행했다는 점도 포인트
                int x = (l1 != null) ? l1.val : 0;
                int y = (l2 != null) ? l2.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            return dummyHead.next;
        }
    }

    public static void main(String[] args) {
//        Solution1.ListNode listNode1 = new Solution1.ListNode(2);
//        Solution1.ListNode listNode2 = new Solution1.ListNode(4);
//        Solution1.ListNode listNode3 = new Solution1.ListNode(3);
//
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//
//        Solution1.ListNode listNodeC1 = new Solution1.ListNode(5);
//        Solution1.ListNode listNodeC2 = new Solution1.ListNode(6);
//        Solution1.ListNode listNodeC3 = new Solution1.ListNode(4);
//
//        listNodeC1.next = listNodeC2;
//        listNodeC2.next = listNodeC3;
// =====
//        Solution1.ListNode listNode1 = new Solution1.ListNode(0);
//
//        Solution1.ListNode listNodeC1 = new Solution1.ListNode(0);
// =====

        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        ListNode listNodeC1 = new ListNode(9);
        ListNode listNodeC2 = new ListNode(9);
        ListNode listNodeC3 = new ListNode(9);
        ListNode listNodeC4 = new ListNode(9);

        listNodeC1.next = listNodeC2;
        listNodeC2.next = listNodeC3;
        listNodeC3.next = listNodeC4;

        ListNode head = s1.addTwoNumbers(listNode1, listNodeC1);

        do {
            System.out.println("head.val = " + head.val);
            head = head.next;
        } while (head != null);
    }
}
