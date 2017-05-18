/*
 * Leetcode: https://leetcode.com/problems/binary-tree-inorder-traversal/#/description
 *
 * Given a binary tree, return the inorder traversal of its nodes values.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeInorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stk = new Stack<>();
        TreeNode x = root;
        while (x != null) {
            stk.push(x);
            x = x.left;
        }
        while (!stk.isEmpty()) {
            TreeNode y = stk.pop();
            list.add(y.val);
            if (y.right != null) {
                x = y.right;
                while (x != null) {
                    stk.push(x);
                    x = x.left;
                }
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode x) {
        List<Integer> list = new LinkedList<>();
        if (x == null) {
            return list;
        }
        Stack<TreeNode> stk = new Stack<>();
        stk.push(x);
        while (!stk.isEmpty()) {
            TreeNode v = stk.pop();
            if (v.left == null && v.right == null) {
                list.add(v.val);
            } else if (v.left == null) {
                list.add(v.val);
                stk.push(v.right);
                v.right = null;
            } else if (v.right == null) {
                TreeNode temp = v.left;
                v.left = null;
                stk.push(v);
                stk.push(temp);
            } else {
                stk.push(v.right);
                v.right = null;
                TreeNode temp = v.left;
                v.left = null;
                stk.push(v);
                stk.push(temp);
            }
        }
        return list;
    }
}
