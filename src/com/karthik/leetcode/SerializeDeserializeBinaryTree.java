/*
 * Leetcode: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Serialization is the process of converting a data structure or object into a sequence
 * of bits so that it can be stored in a file or memory buffer, or transmitted across a
 * network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String... args) {
        SerializeDeserializeBinaryTree sdet = new SerializeDeserializeBinaryTree();
        System.out.println(sdet.serialize(sdet.testInput()));
        System.out.println(sdet.deserialize(sdet.serialize(sdet.testInput())).val);
    }

    private TreeNode testInput() {
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(4);
        t1.left.right = new TreeNode(1);
        return t1;
    }

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode x = q.remove();
            if (x == null) {
                sb.append("#");
            } else {
                sb.append(x.val);
                q.add(x.left);
                q.add(x.right);
            }
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] values = data.split(";");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode x = q.remove();
            if (!values[i].equals("#")) {
                x.left = new TreeNode(Integer.parseInt(values[i]));
                q.add(x.left);
            }
            i++;
            if (!values[i].equals("#")) {
                x.right = new TreeNode(Integer.parseInt(values[i]));
                q.add(x.right);
            }
            i++;
        }
        return root;
    }
}
