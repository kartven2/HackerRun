/*
 * LeetCode Problem: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/#/description
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

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PopulatingNextRightPointersEachNodeII {

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect(TreeLinkNode x) {
        TreeLinkNode l = x;
        while (l != null) {
            TreeLinkNode c = l;
            TreeLinkNode prev = null, first = null;
            while (c != null) {
                if (first == null && (c.left != null || c.right != null)) {
                    first = c;
                }
                if (c.left != null) {
                    if (prev != null) {
                        prev.next = c.left;
                        prev = null;
                    }
                    prev = c.left;
                }
                if (c.right != null) {
                    if (prev != null) {
                        prev.next = c.right;
                        prev = null;
                    }
                    prev = c.right;
                }
                c = c.next;
            }
            if (first == null) {
                l = null;
            } else if (first.left != null) {
                l = first.left;
            } else if (first.right != null) {
                l = first.right;
            }
        }
    }
}
