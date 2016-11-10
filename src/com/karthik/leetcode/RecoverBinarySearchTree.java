/*
 * Leetcode: https://leetcode.com/problems/recover-binary-search-tree/
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure. 
 * Note:
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution? 
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RecoverBinarySearchTree {

    TreeNode first, second, current;

    private void walkTree(TreeNode x) {
        if (x.left != null) {
            walkTree(x.left);
        }
        if (current != null && x.val < current.val) {
            if (first == null) {
                first = current;
            }
            second = x;
        }
        current = x;
        if (x.right != null) {
            walkTree(x.right);
        }
    }

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        walkTree(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
