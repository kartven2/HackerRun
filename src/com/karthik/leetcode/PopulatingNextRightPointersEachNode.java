/*
 * LeetCode Problem: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/#/description
 * Given a binary tree
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree
 * (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PopulatingNextRightPointersEachNode {

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect2(TreeLinkNode root) {
        List<List<TreeLinkNode>> list = new LinkedList<>();
        build(root, 0, list);
    }

    private void build(TreeLinkNode x, int lvl, List<List<TreeLinkNode>> list) {
        if (x == null) {
            return;
        }
        List<TreeLinkNode> tmp = null;
        if (list.size() == lvl) {
            tmp = new LinkedList<>();
            list.add(tmp);
        } else {
            tmp = list.get(lvl);
        }
        if (!tmp.isEmpty()) {
            tmp.get(tmp.size() - 1).next = x;
        }
        tmp.add(x);
        build(x.left, lvl + 1, list);
        build(x.right, lvl + 1, list);
    }

    public void connect(TreeLinkNode x) {
        if (x == null) {
            return;
        }
        List<TreeLinkNode> q = new LinkedList<>();
        q.add(x);
        while (!q.isEmpty()) {
            List<TreeLinkNode> parent = q;
            q = new LinkedList<>();
            for (int i = 0; i < parent.size(); i++) {
                TreeLinkNode v = parent.get(i);
                TreeLinkNode w = i + 1 < parent.size() ? parent.get(i + 1) : null;
                v.next = w;
                if (v.left != null) {
                    q.add(v.left);
                }
                if (v.right != null) {
                    q.add(v.right);
                }
            }
        }
    }
}
