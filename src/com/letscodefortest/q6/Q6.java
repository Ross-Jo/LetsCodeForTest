package com.letscodefortest.q6;

import java.util.PriorityQueue;

public class Q6 {
    public static Solution1 s1; // too slow
    public static Solution2 s2;

    /**
     * time complexity:
     * - addNum : O(N)
     * - findMedian : O(N)
     * space complexity: O(N)
     * Runtime: 1575 ms, faster than 8.06% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 51.6 MB, less than 17.12% of Java online submissions for Find Median from Data Stream.
     */
    static class Solution1 {
        class Element {
            int number;
            Element next;
            Element (int number) {
                this.number = number;
            }
        }

        Element head;
        int cnt;

        public Solution1() {
            head = new Element(0); // head
            cnt = 0;
        }

        public void addNum(int num) {
            cnt++;
            Element element = new Element(num);
            Element pointer = head;
            while(pointer.next != null) {
                if (element.number <= pointer.next.number) {
                    Element tmp = pointer.next;
                    pointer.next = element;
                    element.next = tmp;
                    return;
                } else {
                    pointer = pointer.next;
                }
            }
            pointer.next = element;
        }

        public double findMedian() {
            int middle = (int) Math.ceil(cnt/2.0);
            boolean isEven = cnt%2 == 0;
            int idx = 0;
            Element pointer = head;
            while(pointer.next!=null) {
                if (idx == middle) {
                    if (isEven) {
                        return (pointer.number + pointer.next.number)/2.0;
                    } else {
                        return pointer.number;
                    }
                }
                pointer = pointer.next;
                idx++;
            }
            return pointer.number;
        }
    }

    static class Solution2 {

    }

    public static void main(String[] args) {
        s1 = new Solution1();
        s2 = new Solution2();
//        s2.addNum(1);
//        s2.addNum(2);
//        System.out.println(s2.findMedian());
//        s2.addNum(3);
//        System.out.println(s2.findMedian());

//        s2.addNum(3);
//        s2.addNum(3);
//        s2.addNum(4);
//        s2.addNum(2);
//        s2.addNum(1);
//        s2.addNum(2);
//        s2.addNum(9);
//        s2.addNum(7);
//        s2.addNum(6);
//        s2.addNum(5);
//        System.out.println(s2.findMedian());

//        s2.addNum(1);
//        System.out.println(s2.findMedian());
    }
}
