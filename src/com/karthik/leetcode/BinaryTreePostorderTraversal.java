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
public class BinaryTreePostorderTraversal {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void postOrder(TreeNode x, List<Integer> result) {
        Stack<TreeNode> stk = new Stack<>();
        TreeNode current = x;
        while (current != null || !stk.isEmpty()) {
            if (current != null) {
                stk.push(current);
                current = current.left;
            } else {
                TreeNode y = stk.peek();
                if (y.right == null) {
                    stk.pop();
                    result.add(y.val);
                    while (!stk.isEmpty() && (stk.peek().right == null
                            || stk.peek().right == y)) {
                        result.add(stk.peek().val);
                        y = stk.pop();
                    }
                    if (!stk.isEmpty()) {
                        current = stk.peek().right;
                    }
                } else {
                    current = y.right;
                }
            }
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        postOrder(root, result);
        return result;
    }
}
