/*
 * LeetCode: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/#/description
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode x) {
        List<List<Integer>> result = new ArrayList<>();
        if (x == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(x);
        List<Integer> sub = new ArrayList<>();
        boolean lr = true;
        int count = q.size();
        while (!q.isEmpty()) {
            TreeNode v = q.remove();
            count--;
            sub.add(v.val);
            if (count == 0) {
                if (lr) {
                    result.add(sub);
                } else {
                    List<Integer> rev = new ArrayList<>();
                    for (int i = sub.size() - 1; i >= 0; i--) {
                        rev.add(sub.get(i));
                    }
                    result.add(rev);
                }
                lr = !lr;
                sub = new ArrayList<>();
            }
            if (v.left != null) {
                q.add(v.left);
            }
            if (v.right != null) {
                q.add(v.right);
            }
            if (count == 0) {
                count = q.size();
            }
        }
        return result;
    }
}
