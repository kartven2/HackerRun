/*
 * LeetCode: https://leetcode.com/problems/kth-smallest-element-in-a-bst/#/description
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ? k ? BST's total elements.
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
 * frequently? How would you optimize the kthSmallest routine?
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KthSmallestElementBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int numNodes(TreeNode x) {
        if (x == null) {
            return 0;
        }
        return 1 + numNodes(x.left) + numNodes(x.right);
    }

    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int cnt = numNodes(root.left);
        if (k == cnt + 1) {
            return root.val;
        } else if (k <= cnt) {
            return kthSmallest2(root.left, k);
        }
        return kthSmallest2(root.right, (k - (cnt + 1)));
    }

    public int kthSmallest(TreeNode x, int k) {
        if (x == null) {
            return 0;
        }
        Stack<TreeNode> stk = new Stack<>();
        while (x != null) {
            stk.push(x);
            x = x.left;
        }
        while (!stk.isEmpty()) {
            TreeNode v = stk.pop();
            k--;
            if (k == 0) {
                return v.val;
            }
            if (v.right != null) {
                TreeNode y = v.right;
                while (y != null) {
                    stk.push(y);
                    y = y.left;
                }
            }
        }
        return -1;
    }
}
