/*
 * LeetCode: https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * Given an integer n, generate all structurally unique BST's
 * (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastUniqueBinarySearchTreesII {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] result = (List<TreeNode>[]) new ArrayList[n + 1];
        result[0] = new ArrayList<>();
        if (n <= 0) {
            return result[0];
        }
        result[0].add(null);
        for (int i = 1; i <= n; i++) {//i=3
            result[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {//j=1
                for (TreeNode left : result[j]) {//1
                    for (TreeNode right : result[i - j - 1]) {//3-1-1 = 1
                        TreeNode root = new TreeNode(j + 1);
                        root.left = left;
                        root.right = clone(right, j + 1);
                        result[i].add(root);
                    }
                }
            }
        }
        return result[n];
    }

    private TreeNode clone(TreeNode x, int offset) {
        if (x == null) {
            return null;
        }
        TreeNode node = new TreeNode(x.val + offset);
        node.left = clone(x.left, offset);
        node.right = clone(x.right, offset);
        return node;
    }
}