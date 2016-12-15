/*
 * LeetCode: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * return [3,2,1].
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreePostorderTraversal2 {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode x) {
        List<Integer> result = new LinkedList<>();
        if (x == null) {
            return result;
        }
        Stack<TreeNode> stk1 = new Stack<>();
        Stack<TreeNode> stk2 = new Stack<>();
        TreeNode c = x;
        stk1.push(c);
        while (!stk1.isEmpty()) {
            stk2.push(stk1.pop());
            c = stk2.peek();
            if (c.left != null) {
                stk1.push(c.left);
            }
            if (c.right != null) {
                stk1.push(c.right);
            }
        }
        while (!stk2.isEmpty()) {
            result.add(stk2.pop().val);
        }
        return result;
    }
}
