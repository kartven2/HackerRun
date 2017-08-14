/*
 * LeetCode: https://leetcode.com/problems/add-one-row-to-tree/description/
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d.
 * The root node is at depth 1.
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1,
 * create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should
 * be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right
 * subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root
 * of the whole original tree, and the original tree is the new root's left subtree
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AddOneRowToTree {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode addOneRow(TreeNode x, int v, int d) {
        TreeNode ans = null;
        if (d == 1) {
            ans = new TreeNode(v);
            ans.left = x;
            return ans;
        }
        if (x == null) {
            return null;
        }
        add(x, v, d - 1);
        return x;
    }

    private void add(TreeNode x, int v, int d) {
        if (d == 1) {
            TreeNode ol = x.left, or = x.right;
            x.left = new TreeNode(v);
            x.right = new TreeNode(v);
            x.left.left = ol;
            x.right.right = or;
            return;
        }
        if (x.left != null) {
            add(x.left, v, d - 1);
        }
        if (x.right != null) {
            add(x.right, v, d - 1);
        }
    }
}
