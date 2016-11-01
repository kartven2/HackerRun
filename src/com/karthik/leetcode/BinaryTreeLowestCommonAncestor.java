/*
 * Leetcode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
 * between two nodes v and w as the lowest node in T that has both v and w as descendants
 * (where we allow a node to be a descendant of itself). 
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeLowestCommonAncestor {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode x, y;
    TreeNode[] nodes;

    private void buildTree(Integer[] bt, int q, int z) {
        int len = bt.length, i = len - 1;
        nodes = new TreeNode[bt.length];
        while (i > 0) {
            if (nodes[i] == null && bt[i] != null) {
                nodes[i] = new TreeNode(bt[i]);
                if (bt[i] == q) {
                    x = nodes[i];
                }
                if (bt[i] == z) {
                    y = nodes[i];
                }
                if (2 * i < len) {
                    nodes[i].left = nodes[2 * i];
                }
                if (2 * i + 1 < len) {
                    nodes[i].right = nodes[2 * i + 1];
                }
            }
            i--;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode x = root;
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(x, null);
        Stack<TreeNode> st = new Stack<>();
        st.push(x);
        while (!st.isEmpty()) {
            x = st.pop();
            if (x.left != null) {
                parent.put(x.left, x);
                st.push(x.left);
            }
            if (x.right != null) {
                parent.put(x.right, x);
                st.push(x.right);
            }
        }
        Set<TreeNode> ancestor = new HashSet<>();
        x = q;
        while (parent.get(x) != null) {
            ancestor.add(x);
            x = parent.get(x);
        }
        ancestor.add(x);
        x = p;
        while (!ancestor.contains(x)) {
            x = parent.get(x);
        }
        return x;
    }

    public static void main(String... args) {
        BinaryTreeLowestCommonAncestor btc = new BinaryTreeLowestCommonAncestor();
        btc.buildTree(new Integer[]{0, 37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null, null, null, 8}, -100, -71);
        TreeNode ans = btc.lowestCommonAncestor(btc.nodes[1], btc.x, btc.y);
        System.out.println(ans.val);

    }
}