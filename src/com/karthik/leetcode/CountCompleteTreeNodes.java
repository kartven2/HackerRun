/*
 * Leetcode: https://leetcode.com/problems/count-complete-tree-nodes/
 *
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CountCompleteTreeNodes {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;
        int h = 0;
        while (right != null) {
            right = right.right;
            left = left.left;
            h++;
        }
        if (left == null) {
            return (1 << h) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
