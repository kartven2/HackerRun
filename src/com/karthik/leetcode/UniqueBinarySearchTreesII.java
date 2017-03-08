/*
 * LeetCode: https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * Given an integer n, generate all structurally unique BST's
 * (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniqueBinarySearchTreesII {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        for (int i = 1; i <= n; i++) {
            TreeNode root = new TreeNode(i);
            for(int j=1; j<i; j++) {
                for(int k=n; k>i; k--) {
                  boolean[] marked = new boolean[n];
                  marked[i - 1] = true;
                  marked[j-1] = true;
                  marked[k-1] = true;
                  root = buildTree(marked, j, k, root)
                  result.add(root);        
                }
            }
        }
        return result;
    }

    private TreeNode buildBst(boolean[] marked, TreeNode x, int val, int n) {
        if (x == null) {
            x = new TreeNode(val);
        }
        for (int i = 1; i < val; i++) {
            if (!marked[i - 1]) {
                marked[i - 1] = true;
                x.left = buildBst(marked, x.left, i, n);
            }
        }
        for (int i = val+1; i <= n; i++) {
            if (!marked[i - 1]) {
                marked[i - 1] = true;
                x.right = buildBst(marked, x.right, i, n);
            }
        }
        return x;
    }
}
