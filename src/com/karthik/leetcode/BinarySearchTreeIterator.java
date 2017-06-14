/*
 * LeetCode: https://leetcode.com/problems/binary-search-tree-iterator/#/description
 * Implement an iterator over a binary search tree (BST). Your iterator
 * will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinarySearchTreeIterator {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class BSTIterator {

        private Stack<TreeNode> stk;

        private void loadStack(TreeNode x) {
            while (x != null) {
                stk.push(x);
                x = x.left;
            }
        }

        public BSTIterator(TreeNode x) {
            this.stk = new Stack<>();
            loadStack(x);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stk.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (stk.isEmpty()) {
                return -1;
            }
            TreeNode v = stk.pop();
            if (v.right != null) {
                loadStack(v.right);
            }
            return v.val;
        }
    }
}
