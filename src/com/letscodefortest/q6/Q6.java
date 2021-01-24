package com.letscodefortest.q6;

import java.util.*;

// question : https://leetcode.com/problems/find-median-from-data-stream

public class Q6 {
    public static Solution1 s1; // too slow
    public static Solution2 s2;
    public static Solution3 s3;
    public static Solution4 s4;
    public static Solution5 s5;
    public static Solution6 s6;


    /**
     * time complexity:
     * - addNum : O(N)
     * - findMedian : O(N)
     * space complexity: O(N)
     * Runtime: 1575 ms, faster than 8.06% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 51.6 MB, less than 17.12% of Java online submissions for Find Median from Data Stream.
     * 링크드 리스트를 이용한 방법
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
     * 중간값을 찾을때 소팅하는 방법
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
     * 이진탐색을 통해 add시 정렬을 유지하는 방법
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

    /**
     * time complexity:
     * - addNum : O(logN)
     * - findMedian : O(1)
     * space complexity: O(N)
     * Runtime: 78 ms, faster than 20.86% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 68.8 MB, less than 6.09% of Java online submissions for Find Median from Data Stream.
     * 우선순위 큐 2개를 이용해서 값을 2가지로 분류(작은 값들, 큰 값들)하는 동시에 2개 큐의 크기제한을 통해서 중간값을 찾는 방법
     */
    static class Solution4 {
        PriorityQueue<Integer> max_heap;
        PriorityQueue<Integer> min_heap;

        public Solution4() {
            max_heap = new PriorityQueue<>(Collections.reverseOrder());
            min_heap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (max_heap.size() == 0 || max_heap.peek() >= num) {
                max_heap.add(num);
            } else {
                min_heap.add(num);
            }

            if (max_heap.size() - min_heap.size() > 1) {
                min_heap.add(max_heap.poll());
            } else if (max_heap.size() < min_heap.size()) {
                max_heap.add(min_heap.poll());
            }
        }

        public double findMedian() {
            if (max_heap.size() > min_heap.size()) {
                return max_heap.peek();
            }
            return max_heap.size() > 0 ? (max_heap.peek() + min_heap.peek()) / 2.0 : 0;
        }
    }

    /**
     * Same with solution above, but more simplified code
     */
    static class Solution5 {
        PriorityQueue<Integer> max_heap;
        PriorityQueue<Integer> min_heap;

        public Solution5() {
            max_heap = new PriorityQueue<>(Collections.reverseOrder());
            min_heap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            max_heap.add(num);
            min_heap.add(max_heap.poll());
            if(max_heap.size() < min_heap.size()) max_heap.add(min_heap.poll());
        }

        public double findMedian() {
            return max_heap.size() > min_heap.size() ? max_heap.peek() : (max_heap.peek() + min_heap.peek()) / 2.0;
        }
    }

    /**
     * time complexity:
     * - addNum : O(logN)
     * - findMedian : O(1)
     * space complexity: O(N)
     * Runtime: 66 ms, faster than 23.80% of Java online submissions for Find Median from Data Stream.
     * Memory Usage: 54.9 MB, less than 11.90% of Java online submissions for Find Median from Data Stream.
     * Java TreeSet을 이용한 c++의 multiset mocking solution
     */
    static class Solution6 {
        static class Node {
            int value;
            int count;

            public Node(int value, int count){
                this.value = value;
                this.count = count;
            }
        }

        TreeSet<Node> multiSet;
        int count = 0;
        Node loMedian, hiMedian;

        public Solution6() {
            this.multiSet = new TreeSet<>((a, b) -> {
               if (a.value == b.value) return a.count - b.count;
               else return a.value - b.value;
            });
        }

        public void addNum(int num) {
            int size = multiSet.size();
            Node n = new Node(num, count++);
            multiSet.add(n);

            if (size == 0) {
                loMedian = n;
                hiMedian = n;
            } else if (size % 2 != 0) {
                if (n.value < loMedian.value) {
                    loMedian = multiSet.lower(loMedian);
                } else {
                    hiMedian = multiSet.higher(hiMedian);
                }
            } else {
                if (n.value > loMedian.value && n.value < hiMedian.value) {
                    loMedian = multiSet.higher(loMedian);
                    hiMedian = multiSet.lower(hiMedian);
                } else if (n.value >= hiMedian.value) {
                    loMedian = multiSet.higher(loMedian);
                } else {
                    loMedian = hiMedian = multiSet.lower(hiMedian);
                }
            }
        }

        public double findMedian() {
            if (loMedian == null && hiMedian == null) return 0;
            else return (loMedian.value + hiMedian.value) / 2.0;
        }
    }

    public static void main(String[] args) {
        s1 = new Solution1();
        s2 = new Solution2();
        s3 = new Solution3();
        s4 = new Solution4();

//        s4.addNum(1);
//        s4.addNum(2);
//        System.out.println(s4.findMedian());
//        s4.addNum(3);
//        System.out.println(s4.findMedian());

//        s4.addNum(3);
//        s4.addNum(3);
//        s4.addNum(4);
//        s4.addNum(2);
//        s4.addNum(1);
//        s4.addNum(2);
//        s4.addNum(9);
//        s4.addNum(7);
//        s4.addNum(6);
//        s4.addNum(5);
//        System.out.println(s4.findMedian());

        s4.addNum(1);
        System.out.println(s4.findMedian());
    }
}
