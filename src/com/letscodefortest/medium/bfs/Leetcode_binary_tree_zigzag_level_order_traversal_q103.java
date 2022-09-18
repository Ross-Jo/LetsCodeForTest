package com.letscodefortest.medium.bfs;

import java.util.*;

public class Leetcode_binary_tree_zigzag_level_order_traversal_q103 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * deque 및 null node를 통해 tree의 level order를 지그재그로 순회한다
     */
    static class Solution1 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }

            List<List<Integer>> results = new ArrayList<List<Integer>>();

            LinkedList<TreeNode> node_queue = new LinkedList<>();
            node_queue.addLast(root);
            node_queue.addLast(null); // BFS 순회시 null을 이용해 level을 구분하는 점이 눈여겨 볼 부분

            LinkedList<Integer> level_list = new LinkedList<Integer>();
            boolean is_order_left = true;

            while (node_queue.size() > 0) {
                TreeNode curr_node = node_queue.pollFirst();
                if (curr_node != null) {
                    if (is_order_left) {
                        level_list.addLast(curr_node.val);
                    } else {
                        level_list.addFirst(curr_node.val);
                    }

                    if (curr_node.left != null) {
                        node_queue.addLast(curr_node.left);
                    }
                    if (curr_node.right != null) {
                        node_queue.addLast(curr_node.right);
                    }

                } else {
                    results.add(level_list);
                    level_list = new LinkedList<>();
                    if (node_queue.size() > 0) {
                        node_queue.addLast(null);
                    }
                    is_order_left = !is_order_left;
                }
            }

            return results;
        }
    }

    /**
     * time complexity: O(N)
     * space complexity: O(N)
     * DFS를 통해서 순회하는데 global array를 통해서 level 마다의 지그재그 순회를 저장한다
     */
    static class Solution2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            List<List<Integer>> results = new ArrayList<>();
            DFS(root, 0, results);
            return results;
        }

        protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
            if (level >= results.size()) {
                LinkedList<Integer> newLevel = new LinkedList<>();
                newLevel.add(node.val);
                results.add(newLevel);
            } else {
                if (level % 2 == 0) {
                    results.get(level).add(node.val);
                } else {
                    results.get(level).add(0, node.val);
                }
            }

            if (node.left != null) DFS(node.left, level + 1, results);
            if (node.right != null) DFS(node.right, level + 1, results);
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(0);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(-1);
        TreeNode f = new TreeNode(5);
        TreeNode g = new TreeNode(1);
        TreeNode h = new TreeNode(6);
        TreeNode i = new TreeNode(8);

        root.left = a;
        root.right = b;
        a.left = c;
        c.left = f;
        c.right = g;
        b.left = d;
        b.right = e;
        d.right = h;
        e.right = i;

        System.out.println(s1.zigzagLevelOrder(root));
    }
}
