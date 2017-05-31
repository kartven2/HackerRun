/*
 * https://leetcode.com/problems/symmetric-tree/#/description
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SymmetricTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Pair {

        TreeNode a;
        TreeNode b;

        Pair(TreeNode a, TreeNode b) {
            this.a = a;
            this.b = b;
        }

        boolean isNull() {
            return a == null && b == null;
        }

        boolean same() {
            if (isNull()) {
                return true;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }
            return true;
        }
    }

    public boolean isSymmetric2(TreeNode x) {
        if (x == null) {
            return true;
        }
        Stack<Pair> stk = new Stack<>();
        stk.push(new Pair(x.left, x.right));
        while (!stk.isEmpty()) {
            Pair v = stk.pop();
            if (!v.same()) {
                return false;
            }
            if (!v.isNull()) {
                stk.push(new Pair(v.a.left, v.b.right));
                stk.push(new Pair(v.a.right, v.b.left));
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode x) {
        if (x == null) {
            return true;
        }
        return symmetric(x.left, x.right);
    }

    private boolean symmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null || l.val != r.val) {
            return false;
        }
        return symmetric(l.left, r.right) && symmetric(l.right, r.left);
    }
}
