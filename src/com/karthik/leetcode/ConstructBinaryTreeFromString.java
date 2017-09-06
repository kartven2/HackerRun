/*
 * Leetcode: https://leetcode.com/problems/construct-binary-tree-from-string/description/
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConstructBinaryTreeFromString {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode str2tree(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        TreeNode root = build(null, s);
        return root;
    }

    private TreeNode build(TreeNode x, String s) {
        int n = s.length();
        if (n == 0) {
            return null;
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n && s.charAt(i) != '(') {
            sb.append(s.charAt(i++));
        }
        x = new TreeNode(Integer.parseInt(sb.toString()));
        if (i == n) {
            return x;
        }
        int cnt = 1, st = ++i;
        while (i < n && cnt > 0) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
            }
            i++;
        }
        x.left = build(x.left, s.substring(st, i - 1));
        if (i == n) {
            return x;
        }
        if (s.charAt(i) == '(') {
            i++;
        }
        x.right = build(x.right, s.substring(i, n - 1));
        return x;
    }
}
