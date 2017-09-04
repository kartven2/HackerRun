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

    private int result = Integer.MIN_VALUE;

    private int max(int a, int b, int c) {
        return a > b && a > c ? a : b > c ? b : c;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int maxPathSum(TreeNode x) {
        findMax(x);
        return result;
    }

    private int findMax(TreeNode x) {
        if (x == null) {
            return 0;
        }
        int lmax = findMax(x.left);
        int rmax = findMax(x.right);
        int maxp = max(x.val + lmax, x.val + rmax, x.val);
        int maxs = max(maxp, lmax + rmax + x.val);
        result = max(result, maxs);
        return maxp;
    }
}
