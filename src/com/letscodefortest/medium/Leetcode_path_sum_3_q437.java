package com.letscodefortest.medium;

public class Leetcode_path_sum_3_q437 {

    static Solution1 s1 = new Solution1();

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

    // 완전탐색
    static class Solution1 {
        int hit = 0;

        public int pathSum(TreeNode root, long targetSum) {
            dfs(root, targetSum);
            return hit;
        }

        void dfs(TreeNode node, long targetSum) {
            if (node == null) {
                return;
            }
            sum(node, targetSum);
            dfs(node.left, targetSum);
            dfs(node.right, targetSum);
        }

        void sum(TreeNode n, long s) {
            if (n == null) {
                return;
            }
            long temp = s - n.val;
            if (temp == 0) {
                hit++;
            }
            sum(n.left, temp);
            sum(n.right, temp);
        }
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(-3);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(11);
        TreeNode n7 = new TreeNode(3);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n9 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n5.right = n9;

        System.out.println(s1.pathSum(n1, 8));
    }
}
