/*
 * LeetCode: https://leetcode.com/problems/equal-tree-partition/description/
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EqualTreePartition {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int sum = 0;

    public boolean checkEqualTree(TreeNode x) {
        if (x == null) {
            return false;
        }
        sum = walk(x);
        return canSplit(x);
    }

    private int walk(TreeNode x) {
        if (x == null) {
            return 0;
        }
        x.val += (walk(x.left) + walk(x.right));
        return x.val;
    }

    private boolean canSplit(TreeNode x) {
        if (x == null) {
            return false;
        }
        if (x.left != null && (sum - x.left.val) == x.left.val) {
            return true;
        }
        if (x.right != null && (sum - x.right.val) == x.right.val) {
            return true;
        }
        return canSplit(x.left) || canSplit(x.right);
    }
}
