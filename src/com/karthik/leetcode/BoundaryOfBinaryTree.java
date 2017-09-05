/*
 * LeetCode: https://leetcode.com/problems/boundary-of-binary-tree/description/
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BoundaryOfBinaryTree {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode x) {
        List<Integer> result = new ArrayList<>();
        if (x == null) {
            return result;
        }
        result.add(x.val);
        leftTreeBoundaryAndEdges(x.left, true, result);
        rightTreeBoundaryAndEdges(x.right, true, result);
        return result;
    }

    private void leftTreeBoundaryAndEdges(TreeNode x, boolean isBoundary, List<Integer> result) {
        if (x == null) {
            return;
        }
        if (isBoundary || (x.left == null && x.right == null)) {
            result.add(x.val);
            if (x.left == null && x.right == null) {
                return;
            }
        }
        leftTreeBoundaryAndEdges(x.left, isBoundary, result);
        leftTreeBoundaryAndEdges(x.right, isBoundary && (x.left == null), result);
    }

    private void rightTreeBoundaryAndEdges(TreeNode x, boolean isBoundary, List<Integer> result) {
        if (x == null) {
            return;
        }
        if (x.left == null && x.right == null) {
            result.add(x.val);
            return;
        }
        rightTreeBoundaryAndEdges(x.left, isBoundary && (x.right == null), result);
        rightTreeBoundaryAndEdges(x.right, isBoundary, result);
        if (isBoundary) {
            result.add(x.val);
        }
    }
}
