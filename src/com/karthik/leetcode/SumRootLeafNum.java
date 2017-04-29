/*
 * Leetcode: https://leetcode.com/problems/sum-root-to-leaf-numbers/#/description
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SumRootLeafNum {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Sample {

        TreeNode x;
        String prefix;

        Sample(TreeNode x, String prefix) {
            this.x = x;
            this.prefix = prefix;
        }
    }

    public int sumNumbers(TreeNode x) {
        if (x == null) {
            return 0;
        }
        Stack<Sample> stk = new Stack<>();
        stk.add(new Sample(x, ""));
        int ans = 0;
        while (!stk.isEmpty()) {
            Sample c = stk.pop();
            String pre = c.prefix;
            TreeNode v = c.x;
            StringBuilder sb = new StringBuilder(pre);
            sb.append(v.val);
            if (v.left == null && v.right == null) {
                ans += Integer.parseInt(sb.toString());
                continue;
            }
            if (v.left != null) {
                stk.push(new Sample(v.left, sb.toString()));
            }
            if (v.right != null) {
                stk.push(new Sample(v.right, sb.toString()));
            }
        }
        return ans;
    }
}
