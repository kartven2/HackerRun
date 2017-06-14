/*
 * LeetCode: https://leetcode.com/problems/binary-tree-preorder-traversal/#/description
 * Given a binary tree, return the preorder traversal of its nodes' values.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreePreorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode x) {
        List<Integer> result = new LinkedList<>();
        if (x == null) {
            return result;
        }
        Stack<TreeNode> stk = new Stack<>();
        stk.push(x);
        while (!stk.isEmpty()) {
            TreeNode y = stk.pop();
            while (y != null) {
                result.add(y.val);
                if (y.right != null) {
                    stk.push(y.right);
                }
                y = y.left;
            }
        }
        return result;
    }
}
