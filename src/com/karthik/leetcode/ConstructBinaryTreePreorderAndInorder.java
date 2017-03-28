/*
 * Leetcode: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/#/description
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConstructBinaryTreePreorderAndInorder {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String...args) {
        ConstructBinaryTreePreorderAndInorder cb = new ConstructBinaryTreePreorderAndInorder();
        cb.buildTree(new int[] {2,1}, new int[] {1,2});
    }
    
    class Multimap {

        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();

        void add(int key, int idx) {
            m1.put(key, idx);
            m2.put(idx, key);
        }

        int getIdx(int key) {
            return m1.get(key);
        }

        int getKey(int idx) {
            return m2.get(idx);
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }

        Multimap pre = new Multimap();
        for (int i = 0; i < preorder.length; i++) {
            pre.add(preorder[i], i);
        }

        List<Integer> list = new ArrayList<>();
        for (int x : inorder) {
            list.add(x);
        }
        return buildTree(pre, 0, null, list);
    }

    private TreeNode buildTree(Multimap pre, Integer pi, TreeNode x, List<Integer> list) {
        int root = pre.getKey(pi);
        if (x == null) {
            x = new TreeNode(root);
        }
        if (list.size() == 1) {
            return x;
        }
        List<Integer> leftChld = new ArrayList<>();
        List<Integer> rightChld = new ArrayList<>();
        List<Integer> ptr = leftChld;
        Integer lidx = null, ridx = null, idx = null;
        for (int key : list) {
            if (key == root) {
                ptr = rightChld;
                lidx = idx;
                idx = null;
            } else {
                ptr.add(key);
                if (idx==null || pre.getIdx(key) < idx) {
                    idx = pre.getIdx(key);
                }
            }
        }
        ridx = idx;
        if (lidx!=null) {
            x.left = buildTree(pre, lidx, null, leftChld);
        }
        if (ridx!=null) {
            x.right = buildTree(pre, ridx, null, rightChld);
        }
        return x;
    }
}
