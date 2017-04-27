/*
 * LeetCode: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/#/description
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConstructBinaryTreeInorderPostorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null 
                || postorder == null 
                || inorder.length == 0 
                || postorder.length == 0 
                || inorder.length != postorder.length) {
            return null;
        }
        TreeNode x = buildTree(inorder, postorder, postorder.length-1, null);
        return x;
    }
    
    private TreeNode buildTree(int[] in, int[] po, int ri, TreeNode x) {
        if(ri<0) {
            return x;
        }
        if(x==null) {
            x = new TreeNode(ri);
        }
        
    }
}
