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
            boolean[] marked = new boolean[n];
            marked[i - 1] = true;
            root = buildBst(marked, root, i, n);
            if (root != null) {
                result.add(root);
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

    public List<TreeNode> generateTrees2(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }
        while (true) {
            TreeNode node = getTree(arr, 1, n);
            if (node != null) {
                result.add(node);
            }
            int x = n - 1;
            while (x > 0 && arr[x + 1] < arr[x]) {
                x--;
            }
            if (x == 0) {
                break;
            }
            int y = n;
            while (y > x && arr[y] < arr[x]) {
                y--;
            }
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
            for (int i = x + 1, j = n; i < j; i++, j--) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return result;
    }
}
