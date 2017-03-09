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
public class UniqueBinarySearchTreesII {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<TreeNode> buildTree(int lo, int hi) {
        List<TreeNode> result = new ArrayList<>();
        if (lo > hi) {
            result.add(null);
            return result;
        }
        if (lo == hi) {
            result.add(new TreeNode(lo));
            return result;
        }
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftList = buildTree(lo, i - 1);
            List<TreeNode> rightList = buildTree(i + 1, hi);
            for (int j = 0; j < leftList.size(); j++) {
                for (int k = 0; k < rightList.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftList.get(j);
                    root.right = rightList.get(k);
                    result.add(root);
                }
            }
        }
        return result;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return buildTree(1, n);
    }
}
