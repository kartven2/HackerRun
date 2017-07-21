/*
 * LeetCode: https://leetcode.com/problems/serialize-and-deserialize-bst/#/description
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SerializeDeserializeBST {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode x) {
            StringBuilder sb = new StringBuilder();
            if (x == null) {
                return sb.toString();
            }
            Stack<TreeNode> stk = new Stack<>();
            stk.push(x);
            while (!stk.isEmpty()) {
                TreeNode v = stk.pop();
                sb.append(v.val);
                sb.append(",");
                if (v.right != null) {
                    stk.add(v.right);
                }
                if (v.left != null) {
                    stk.add(v.left);
                }
            }
            return sb.substring(0, sb.toString().length() - 1);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            String[] ip = data.split(",");
            int n = ip.length;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(ip[i]);
            }
            Stack<TreeNode> stk = new Stack<>();
            TreeNode root = new TreeNode(a[0]);
            stk.push(root);
            for (int i = 1; i < n; i++) {
                root = build(root, a[i]);
            }
            return root;
        }

        private TreeNode build(TreeNode x, int key) {
            if (x == null) {
                x = new TreeNode(key);
            }
            if (x.val < key) {
                x.right = build(x.right, key);
            } else if (x.val > key) {
                x.left = build(x.left, key);
            }
            return x;
        }
    }
}
