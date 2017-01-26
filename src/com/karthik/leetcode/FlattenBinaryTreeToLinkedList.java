/*
 * LeetCode Problem: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * Given a binary tree, flatten it to a linked list in-place.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FlattenBinaryTreeToLinkedList {

    class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean isLeaf(TreeNode x) {
        return x.left == null && x.right == null;
    }

    public void flatten(TreeNode x) {
        if (x == null || isLeaf(x)) {
            return;
        }
        modify(x);
    }

    private TreeNode rightLeaf(TreeNode y) {
        while (y.right != null) {
            y = y.right;
        }
        return y;
    }

    private TreeNode modify(TreeNode x) {
        if (isLeaf(x)) {
            return x;
        } else if (x.left == null) {
            x.right = modify(x.right);
            return x;
        } else if (x.right == null) {
            TreeNode tmp = x.left;
            x.left = null;
            x.right = modify(tmp);
            return x;
        }
        TreeNode lefttmp = x.left;
        TreeNode righttmp = x.right;
        x.left = null;
        x.right = modify(lefttmp);
        TreeNode lastNode = rightLeaf(x);
        lastNode.right = modify(righttmp);
        return x;
    }
}
