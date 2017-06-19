/*
 * LeetCode: https://leetcode.com/problems/binary-tree-right-side-view/#/description
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeRightSideView {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode x) {
        List<Integer> result = new LinkedList<>();
        if (x == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(x);
        int sz = q.size();
        while (!q.isEmpty()) {
            TreeNode v = q.remove();
            sz--;
            if (v.left != null) {
                q.add(v.left);
            }
            if (v.right != null) {
                q.add(v.right);
            }
            if (sz == 0) {
                result.add(v.val);
                sz = q.size();
            }
        }
        return result;
    }
}
