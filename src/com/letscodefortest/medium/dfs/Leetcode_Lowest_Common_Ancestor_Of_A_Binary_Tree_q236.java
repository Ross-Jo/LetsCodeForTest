package com.letscodefortest.medium.dfs;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

public class Leetcode_Lowest_Common_Ancestor_Of_A_Binary_Tree_q236 {
    static Solution1 s1 = new Solution1();

    /**
     * time complexity: O(N)
     * space complexity: O(d), d: 최대 깊이
     * Runtime: 8 ms, faster than 65.50% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 47.6 MB, less than 33.70% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     */
    static class Solution1 {
        static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        TreeNode ans = null;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root, p, q);
            return ans;
        }

        public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;

            boolean left = false, right = false, here = false;

            if (root.left != null) {
                left = dfs(root.left, p, q); // 왼쪽 트리에서 타깃 노드가 찾아지는지 확인한다.
            }

            if (root.right != null) {
                right = dfs(root.right, p, q); // 오른쪽 트리에서 타깃 노드가 찾아지는지 확인한다.
            }

            if (left && right) { // 만약 왼쪽과 오른쪽 노드에서 모두 타깃 노드를 찾았다면 LCA를 기록한다
                ans = root;
            }

            if (root.val == p.val || root.val == q.val) { // 만약 왼쪽이나 오른쪽 트리에서 타깃을 찾고 남은 타깃이 공통 조상 노드라면
                here = true; // 해당 케이스 인지 표시하고
                if (left || right) {
                    ans = root; // LCA를 기록한다
                }
            }

            return left || right || here; // 타깃노드를 하나라도 찾았는지 결과를 propagate 하기 위함이다
        }
    }

    public static void main(String[] args) {
        Solution1.TreeNode three = new Solution1.TreeNode(3);
        Solution1.TreeNode five = new Solution1.TreeNode(5);
        Solution1.TreeNode one = new Solution1.TreeNode(1);
        Solution1.TreeNode six = new Solution1.TreeNode(6);
        Solution1.TreeNode two = new Solution1.TreeNode(2);
        Solution1.TreeNode seven = new Solution1.TreeNode(7);
        Solution1.TreeNode four = new Solution1.TreeNode(4);
        Solution1.TreeNode zero = new Solution1.TreeNode(0);
        Solution1.TreeNode eight = new Solution1.TreeNode(8);

        three.left = five;
        three.right = one;
        five.left = six;
        five.right = two;
        two.left = seven;
        two.right = four;
        one.left = zero;
        one.right = eight;

//        System.out.println(s1.lowestCommonAncestor(three, five, one).val);
//        System.out.println(s1.lowestCommonAncestor(three, five, four).val);
        System.out.println(s1.lowestCommonAncestor(three, five, three).val);
    }
}
