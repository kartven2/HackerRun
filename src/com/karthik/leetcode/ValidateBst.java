/*
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST). 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidateBst {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode x) {
        if (x == null) {
            return true;
        }
        return isValidBst(x.left, Long.MIN_VALUE, (long) x.val) && isValidBst(x.right, (long) x.val, Long.MAX_VALUE);
    }

    public boolean isValidBst(TreeNode x, long min, long max) {
        if (x == null) {
            return true;
        }
        if (x.val <= min || x.val >= max) {
            return false;
        }
        return isValidBst(x.left, min, x.val) && isValidBst(x.right, x.val, max);
    }
}
