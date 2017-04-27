/*
 * LeetCode: https://leetcode.com/problems/delete-node-in-a-bst/#/description
 * Given a root node reference of a BST and a key,
 * delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DeleteNodeBST {

    public static void main(String... args) {
        DeleteNodeBST dbs = new DeleteNodeBST();
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        dbs.deleteNode(t, 2);
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode deleteNode2(TreeNode x, int key) {
        if (x == null) {
            return null;
        }
        x = delete(x, key);
        return x;
    }

    private TreeNode delete(TreeNode x, int key) {
        if (x == null) {
            return null;
        }
        if (x.val < key) {
            x.right = delete(x.right, key);
        } else if (x.val > key) {
            x.left = delete(x.left, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            TreeNode t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    private TreeNode min(TreeNode x) {
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x;
    }

    private TreeNode deleteMin(TreeNode x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    public TreeNode deleteNode(TreeNode x, int key) {
        if (x == null) {
            return null;
        }
        TreeNode c = x, p = null;
        boolean leftChild = true;
        while (c != null) {
            if (c.val == key) {
                break;
            } else if (c.val < key) {
                p = c;
                leftChild = false;
                c = c.right;
            } else {
                p = c;
                leftChild = true;
                c = c.left;
            }
        }
        if (c == null || c.val != key) {
            return x;
        }
        if (c.left == null && c.right == null) {
            if (p == null) {
                return null;
            } else if (leftChild) {
                p.left = null;
            } else {
                p.right = null;
            }
        } else if (c.left == null) {
            if (p == null) {
                return c.right;
            } else if (leftChild) {
                p.left = c.right;
            } else {
                p.right = c.right;
            }
        } else if (c.right == null) {
            if (p == null) {
                return c.left;
            } else if (leftChild) {
                p.left = c.left;
            } else {
                p.right = c.left;
            }
        } else {
            TreeNode t = c;
            c = min(t.right);
            c.right = deleteMin(t.right);
            c.left = t.left;
            if (p == null) {
                return c;
            } else if (leftChild) {
                p.left = c;
            } else {
                p.right = c;
            }
        }
        return x;
    }
}
