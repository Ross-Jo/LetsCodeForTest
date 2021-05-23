package com.letscodefortest.medium;

// https://leetcode.com/problems/validate-binary-search-tree/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * time complexity: O(N)
 * space complexity: O(N)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
 * Memory Usage: 41.2 MB, less than 5.04% of Java online submissions for Validate Binary Search Tree.
 * valid한 BST인지를 알기 위해서는 항상 leftSubTree의 최대값이 leftSubTree의 부모 노드보다는 작아야 하고, rightSubTree의 최소값이 부모 노드보다는
 * 커야한다. 이는 트리의 중위순회로 구현될 수 있으며 트리의 중위순회시 last value 값을 기록해서 해당 Tree가 BST인지 판별가능하다.
 */
public class Leetcode_q98 {
    static Solution1 s1 = new Solution1();

    public static class TreeNode {
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
        long lastVal = Long.MIN_VALUE; // 주어진 최소값이 -2^31이기 떄문에 long.MIN_VALUE로 문제에서 주어질 수 있는 범위보다 더 작은값(확실한 최소값)을 사용
                                       // 해당 값은 BST 검증시 살펴본 '마지막 값'을 의미함
        boolean result = true;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            if (root.left != null) isValidBST(root.left);

            if (lastVal >= root.val) return result = false; // lastVal이 만약 루트값보다 크거나 같은 경우 BST 조건이 깨지기 때문에 최종 판별 결과를 false 처리
            lastVal = root.val; // leftSubTree 검증 후 lastVal 변수를 이용해 루트 값 갱신

            if (root.right != null) isValidBST(root.right);

            return result;
        }
    }

    // leetcode solution

    // solution1. 재귀호출시 valid한 값의 범위가 어디인지 유지하면서 계속적인 호출을 하는 방법
    // 시간복잡도 : O(n) - 각 노드 1번 방문, 공간복잡도 : O(n) ex. skewed tree
    static class Solution2 {
        public boolean validate(TreeNode root, Integer low, Integer high) {
            // Empty trees are valid BSTs.
            if (root == null) {
                return true;
            }
            // The current node's value must be between low and high.
            if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
                return false;
            }
            // The left and right subtree must also be valid.
            return validate(root.right, root.val, high) && validate(root.left, low, root.val);
        }

        public boolean isValidBST(TreeNode root) {
            return validate(root, null, null);
        }
    }

    // solution2. 위 방식에 대한 iterative 한 솔루션. double ended queue를 이용
    // 시간복잡도 : O(n) - 각 노드 1번 방문, 공간복잡도 : O(n)
    static class Solution3 {
        private Deque<TreeNode> stack = new LinkedList();
        private Deque<Integer> upperLimits = new LinkedList();
        private Deque<Integer> lowerLimits = new LinkedList();

        public void update(TreeNode root, Integer low, Integer high) {
            stack.add(root);
            lowerLimits.add(low);
            upperLimits.add(high);
        }

        public boolean isValidBST(TreeNode root) {
            Integer low = null, high = null, val;
            update(root, low, high);

            while (!stack.isEmpty()) {
                root = stack.poll();
                low = lowerLimits.poll();
                high = upperLimits.poll();

                if (root == null) continue;
                val = root.val;
                if (low != null && val <= low) {
                    return false;
                }
                if (high != null && val >= high) {
                    return false;
                }
                update(root.right, val, high);
                update(root.left, low, val);
            }
            return true;
        }
    }

    // solution3. 역시 inorder 방식을 활용한 solution
    // 시간복잡도 : O(n) in the worst case when the tree is a BST or the "bad" element is a rightmost leaf
    // 공간복잡도 : O(n) for the space on the run-time stack.
    static class Solution4 {
        // We use Integer instead of int as it supports a null value.
        private Integer prev;

        public boolean isValidBST(TreeNode root) {
            prev = null;
            return inorder(root);
        }

        private boolean inorder(TreeNode root) {
            if (root == null) return true;

            if (!inorder(root.left)) return false;

            if (prev != null && root.val <= prev) return false;
            prev = root.val;

            return inorder(root.right);
        }
    }

    // solution3의 iterative한 구현
    // 시간복잡도 : O(n) in the worst case when the tree is a BST or the "bad" element is a rightmost leaf
    // 공간복잡도 : O(n)
    static class Solution5 {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            Integer prev = null;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // If next element in inorder traversal is smaller than the previous one that's not BST.
                if (prev != null && root.val <= prev) {
                    return false;
                }
                prev = root.val;
                root = root.right;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        System.out.println(s1.isValidBST(root));
    }
}
