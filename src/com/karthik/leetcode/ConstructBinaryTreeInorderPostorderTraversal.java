/*
 * LeetCode: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/#/description
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConstructBinaryTreeInorderPostorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String... args) {
        int[] a = {-4, -10, 3, -1, 7, 11, -8, 2};
        int[] b = {-4, -1, 3, -10, 11, -8, 2, 7};
        ConstructBinaryTreeInorderPostorderTraversal cb = new ConstructBinaryTreeInorderPostorderTraversal();
        cb.buildTree(a, b);
    }

    class Result {

        private TreeNode x;
        private int idx;

        Result(TreeNode x, int idx) {
            this.x = x;
            this.idx = idx;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null
                || postorder == null
                || inorder.length == 0
                || postorder.length == 0
                || inorder.length != postorder.length) {
            return null;
        }
        Result x = build(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
        return x.x;
    }

    private Result build(int[] inorder, int lo, int hi, int[] postorder, int ri) {
        if (lo > hi) {
            return new Result(null, ri);
        }
        if (lo == hi) {
            return new Result(new TreeNode(inorder[lo]), ri - 1);
        }
        TreeNode x = new TreeNode(postorder[ri]);
        int mid = 0;
        for (int i = lo; i <= hi; i++) {
            if (inorder[i] == postorder[ri]) {
                mid = i;
                break;
            }
        }
        Result y = build(inorder, mid + 1, hi, postorder, ri - 1);
        x.right = y.x;
        y = build(inorder, lo, mid - 1, postorder, y.idx);
        x.left = y.x;
        return new Result(x, y.idx);
    }
}
