/*
 * LeetCode: https://leetcode.com/problems/inorder-successor-in-bst/description/
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class InorderSuccessorBST {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode x, TreeNode p) {
        if (x == null || p == null) {
            return null;
        }
        if (p.right == null) {
            TreeNode y = null;
            while (x != null && x.val != p.val) {
                if (x.val < p.val) {
                    x = x.right;
                } else {
                    y = x;
                    x = x.left;
                }
            }
            return y;
        }
        return leftMostNode(p.right);
    }

    private TreeNode leftMostNode(TreeNode x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
}