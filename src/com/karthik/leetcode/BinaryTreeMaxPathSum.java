/*
 * Leetcode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence
 * of nodes from some starting node to any node in the tree
 * along the parent-child connections.
 * The path does not need to go through the root.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeMaxPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Result {
        int val;
    }

    private int max(int a, int b, int c) {
        return a > b && a > c ? a : b > c ? b : c;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int maxPathSum(TreeNode x) {
        Result res = new Result();
        res.val = Integer.MIN_VALUE;
        findMax(x, res);
        return res.val;
    }

    private int findMax(TreeNode x, Result res) {
        if (x == null) {
            return 0;
        }
        int lmax = findMax(x.left, res);
        int rmax = findMax(x.right, res);
        int maxp = max(x.val + lmax, x.val + rmax, x.val);
        int maxs = max(maxp, lmax + rmax + x.val);
        res.val = max(res.val, maxs);
        return maxp;
    }
}
