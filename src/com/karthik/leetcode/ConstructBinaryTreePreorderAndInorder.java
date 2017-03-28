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

    private TreeNode buildTree(int[] preorder, int[] inorder, int pi, TreeNode x) {
        if(pi>=preorder.length) {
            
        }
        if(x==null) {
            x = new TreeNode(preorder[pi]);
        }
        List<Integer> left = new ArrayList<>();
        int j=0;
        for(int i=0; i<inorder.length && inorder[i] != preorder[pi]; i++) {
            left.add(inorder[i]);
            j=i;
        }
        j+=2;
        int[] larr = new int[left.size()];
        for(int i=0; i<larr.length; i++) {
            larr[i] = left.get(i);
        }        
        x.left = buildTree(preorder, larr, pi+1, null);
        int[] rarr = new int[inorder.length-j];
        for(int k=0;j<inorder.length; j++, k++) {
            rarr[k] = inorder[j];
        }
        x.right = buildTree(preorder)
    }
    
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return buildTree(preorder, inorder, 0, null);
    }
}

