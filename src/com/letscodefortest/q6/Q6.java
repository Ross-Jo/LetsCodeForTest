package com.letscodefortest.q6;

import java.util.*;

public class Q6 {
    public static Solution1 s1; // too slow
    public static Solution2 s2;
    public static Solution3 s3;

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


    /**
     * time complexity:
     * - addNum : O(1)
     * - findMedian : O(NlogN)
     * space complexity: O(N)
     * Runtime: 1072 ms, faster than 8.15% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 53.6 MB, less than 17.78% of Java online submissions for Find Median from Data Stream.
     */
    static class Solution2 {
        ArrayList<Integer> list;

        public Solution2() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
        }

        public double findMedian() {
            list.sort(Comparator.comparingInt(o -> o));

            double ret;
            int size = list.size();

            if (size % 2 == 0) {
                ret = (list.get(size/2 - 1) + list.get(size/2)) / 2.0;
            } else {
                ret = list.get((int) Math.ceil(size / 2.0) - 1);
            }

            return ret;
        }
    }

    /**
     * time complexity:
     * - addNum : O(logN)
     * - findMedian : O(1)
     * space complexity: O(N)
     * Runtime: 62 ms, faster than 25.48% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 50 MB, less than 90.29% of Java online submissions for Find Median from Data Stream.
     */
    static class Solution3 {
        ArrayList<Integer> list;

        public Solution3() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            if (list.size() == 0) list.add(num);
            else {
                int index = Collections.binarySearch(list, num);
                list.add(index < 0 ? index * (-1) - 1 : index, num);
            }
        }

        public double findMedian() {
            double ret;
            int size = list.size();

            if (size % 2 == 0) {
                ret = (list.get(size/2 - 1) + list.get(size/2)) / 2.0;
            } else {
                ret = list.get((int) Math.ceil(size / 2.0) - 1);
            }

            return ret;
        }
    }

    static class Solution4 {

    }

    static class Solution5 {

    }

    public static void main(String[] args) {
        s1 = new Solution1();
        s2 = new Solution2();
        s3 = new Solution3();

//        s3.addNum(1);
//        s3.addNum(2);
//        System.out.println(s3.findMedian());
//        s3.addNum(3);
//        System.out.println(s3.findMedian());

//        s3.addNum(3);
//        s3.addNum(3);
//        s3.addNum(4);
//        s3.addNum(2);
//        s3.addNum(1);
//        s3.addNum(2);
//        s3.addNum(9);
//        s3.addNum(7);
//        s3.addNum(6);
//        s3.addNum(5);
//        System.out.println(s3.findMedian());

        s3.addNum(1);
        System.out.println(s3.findMedian());
    }
}
