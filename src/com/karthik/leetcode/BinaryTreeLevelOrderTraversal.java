/*
 * LeetCode: https://leetcode.com/problems/binary-tree-level-order-traversal/#/description
 *
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeLevelOrderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode x) {
        List<List<Integer>> result = new LinkedList<>();
        if (x == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(x);
        List<Integer> list = new LinkedList<>();
        int sz = q.size();
        while (!q.isEmpty()) {
            TreeNode v = q.remove();
            sz--;
            list.add(v.val);
            if (v.left != null) {
                q.add(v.left);
            }
            if (v.right != null) {
                q.add(v.right);
            }
            if (sz == 0) {
                result.add(list);
                list = new LinkedList<>();
                sz = q.size();
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        preorder(result, root, 0);
        return result;
    }

    private void preorder(List<List<Integer>> result, TreeNode x, int i) {
        if (x == null) {
            return;
        }
        List<Integer> list = null;
        if (result.size() == i) {
            list = new LinkedList<>();
            list.add(x.val);
            result.add(list);
        } else {
            list = result.get(i);
            list.add(x.val);
        }
        preorder(result, x.left, i + 1);
        preorder(result, x.right, i + 1);
    }
}
