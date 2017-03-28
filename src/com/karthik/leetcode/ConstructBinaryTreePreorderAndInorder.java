/*
 * Leetcode: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/#/description
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConstructBinaryTreePreorderAndInorder {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Result {

        TreeNode x;
        int ridx;

        Result(TreeNode x, int ridx) {
            this.x = x;
            this.ridx = ridx;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        Result res = tree(map, 0, preorder, 0, inorder.length - 1, null);
        return res.x;
    }

    private Result tree(Map<Integer, Integer> map, int pi, int[] preorder, int lo, int hi, TreeNode x) {
        if (lo > hi || pi >= preorder.length) {
            return new Result(null, pi);
        }
        if (x == null) {
            x = new TreeNode(preorder[pi]);
        }
        if (lo == hi) {
            return new Result(x, pi + 1);
        }
        int mid = map.get(preorder[pi]);
        Result left = tree(map, ++pi, preorder, lo, mid - 1, null);
        x.left = left.x;
        Result right = tree(map, left.ridx, preorder, mid + 1, hi, null);
        x.right = right.x;
        return new Result(x, right.ridx);
    }
}
