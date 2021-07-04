package com.letscodefortest.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/delete-nodes-and-return-forest

/**
 * time complexity: O(M * logN)
 * space complexity: O(1)
 * Runtime: 17 ms, faster than 5.63% of Java online submissions for Delete Nodes And Return Forest.
 * Memory Usage: 40.1 MB, less than 18.39% of Java online submissions for Delete Nodes And Return Forest.
 * 삭제노드 마킹 후, 루트 노드 판별 및 마킹 부분 삭제
 */
public class Leetcode_q1110 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

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

    static class Solution1 {
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            List<TreeNode> result = new LinkedList<>();

            // mark
            mark(root, to_delete);

            // collect root
            if (root.val != 0) result.add(root);
            collect(root, result);

            // set null
            for (TreeNode e : result) {
                setNull(e);
            }

            return result;
        }

        private void setNull(TreeNode result) {
            if (result == null) return;
            TreeNode left = result.left;
            TreeNode right = result.right;
            if (left != null) {
                if (left.val == 0) result.left = null;
                else setNull(left);
            }
            if (right != null) {
                if (right.val == 0) result.right = null;
                else setNull(right);
            }
        }

        private void collect(TreeNode root, List<TreeNode> result) {
            if (root == null) return;

            TreeNode left = root.left;
            TreeNode right = root.right;

            if (root.val == 0) {
                if (left != null) {
                    if (left.val != 0) result.add(left);
                    collect(left, result);
                }

                if (right != null) {
                    if (right.val != 0) result.add(right);
                    collect(right, result);
                }
            } else {
                if (left != null) collect(left, result);
                if (right != null) collect(right, result);
            }
        }

        private void mark(TreeNode root, int[] to_delete) {
            if (root == null) return;

            if (Arrays.stream(to_delete).anyMatch(e -> e == root.val)) {
                root.val = 0;
            }

            if (root.left != null) mark(root.left, to_delete);
            if (root.right != null) mark(root.right, to_delete);
        }
    }

    static class Solution2 {
        private final List<TreeNode> list = new ArrayList<>();

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            boolean[] del = new boolean[1001];
            for (int i : to_delete) del[i] = true;

            TreeNode node = helper(root, del);

            if (node != null) list.add(node);
            return list;
        }

        public TreeNode helper(TreeNode node, boolean[] del) {
            if (node == null) return null;
            TreeNode left = helper(node.left, del);
            TreeNode right = helper(node.right, del);

            if (del[node.val]) {
                if (left != null) list.add(left);
                if (right != null) list.add(right);
                return null;
            }

            node.left = left;
            node.right = right;
            return node;
        }

    }

    public static void main(String[] args) {
        TreeNode right2 = new TreeNode(7);
        TreeNode left2 = new TreeNode(6);
        TreeNode right1 = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right = new TreeNode(3, left2, right2);
        TreeNode left = new TreeNode(2, left1, right1);
        TreeNode root = new TreeNode(1, left, right);

        int[] to_delete = new int[]{3, 5};

        s1.delNodes(root, to_delete);
    }
}
