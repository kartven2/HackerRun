/*
 * Leetcode: https://leetcode.com/problems/path-sum-ii/#/description
 *
 * Given a binary tree and a sum,
 * find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 * 
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PathSumII {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> sub = new ArrayList<>();
        computeSum(sub, result, root, sum);
        return result;
    }

    private boolean isLeaf(TreeNode x) {
        return x.left == null && x.right == null;
    }

    private void computeSum(List<Integer> sub, List<List<Integer>> result, TreeNode x, int sum) {
        if (x == null) {
            return;
        }
        if (sum - x.val == 0 && isLeaf(x)) {
            List<Integer> nl = new ArrayList<>();
            for (int y : sub) {
                nl.add(y);
            }
            nl.add(x.val);
            result.add(nl);
            return;
        }
        if (isLeaf(x)) {
            return;
        }
        sub.add(x.val);
        computeSum(sub, result, x.left, sum - x.val);
        computeSum(sub, result, x.right, sum - x.val);
        sub.remove(sub.size() - 1);
    }

    public static void main(String... args) {
        TreeNode x = new TreeNode(-2);
        x.right = new TreeNode(-3);
        PathSumII ps = new PathSumII();
        ps.pathSum(x, -5);
    }
}
