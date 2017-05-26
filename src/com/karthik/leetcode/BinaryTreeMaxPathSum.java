/*
 * Leetcode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence
 * of nodes from some starting node to any node in the tree
 * along the parent-child connections.
 * The path does not need to go through the root.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeMaxPathSum {

    private static final int MIN = Integer.MIN_VALUE;

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int max(int a, int b, int c) {
        return a > b && a > c ? a : b > c ? b : c;
    }

    public int maxPathSum(TreeNode x) {
        if (x == null) {
            return 0;
        }
        if (x.left == null && x.right == null) {
            return x.val;
        }
        if (x.left == null) {
            return dfs(x.right, x.val, x.val);
        }
        if (x.right == null) {
            return dfs(x.left, x.val, x.val);
        }
        int lmax = dfs(x.left, x.val, x.val);
        int rmax = dfs(x.right, x.val, x.val);
        if (x.val < 0) {
            return max(lmax, rmax, lmax + rmax + x.val);
        }
        return lmax + rmax - x.val;
    }

    private int dfs(TreeNode x, int curr, int max) {
        if (x.left == null && x.right == null) {
            curr = max(curr, curr + x.val, x.val);
            return max(max, curr);
        }
        if (x.left == null) {
            if (x.val < 0) {
                max = max(curr, max, x.val);
            }
            curr = max(0, curr + x.val, x.val);
            return dfs(x.right, curr, max);
        }
        if (x.right == null) {
            if (x.val < 0) {
                max = max(curr, max, x.val);
            }
            curr = max(0, curr + x.val, x.val);
            return dfs(x.left, curr, max);
        }
        if (x.val < 0) {
            max = max(curr, max, x.val);
        }
        curr = max(0, curr + x.val, x.val);
        int lmax = dfs(x.left, curr, max);
        int rmax = dfs(x.right, curr, max);
        return max(lmax, rmax, max);
    }

    public static void main(String... args) {
        BinaryTreeMaxPathSum bt = new BinaryTreeMaxPathSum();
        /*TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(1);*/
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        bt.maxPathSum(root);
    }
    
    
    class Tree {

        private int getCapacity(int size) {
            int result = 1;
            while (result < size) {
                result <<= 1;
            }
            result <<= 1;
            return result;
        }

        class TreeNode {

            int val;
            int lidx;
            int ridx;

            TreeNode(int x, int lidx) {
                val = x;
                this.lidx = lidx;
                this.ridx = lidx + 1;
            }

            TreeNode left() {
                return arr[lidx];
            }

            TreeNode right() {
                return arr[ridx];
            }

            @Override
            public String toString() {
                String lstr = arr[lidx] == null ? "" : "" + arr[lidx].val;
                String rstr = arr[ridx] == null ? "" : "" + arr[ridx].val;
                return '{' + "val=" + val + ", left=" + lstr + ", right=" + rstr + '}';
            }

        }

        TreeNode root() {
            return arr[1];
        }

        private TreeNode[] arr;
        private int topIdx;
        private boolean[] marked;

        public Tree(int arrSize) {
            arr = new TreeNode[getCapacity(arrSize)];
            marked = new boolean[getCapacity(arrSize)];
            topIdx = 1;
        }

        private void add(int val) {
            while (marked[topIdx]) {
                topIdx++;
            }
            if (val == MIN) {
                int i = topIdx;
                marked[i] = true;
                while (2 * i < marked.length) {
                    int k = 2 * i;
                    marked[k] = true;
                    marked[k + 1] = true;
                    i = k;
                }
                topIdx++;
                return;
            }
            arr[topIdx] = new TreeNode(val, 2 * topIdx);
            topIdx++;
        }

        private void printTree() {
            printTree(root());
        }

        private void printTree(TreeNode x) {
            if (x == null) {
                return;
            }
            printTree(x.left());
            System.out.println(x.toString());
            printTree(x.right());
        }

        Set<Integer> subtrees = new HashSet<>();

        private boolean isLeaf(TreeNode x) {
            return x.left() == null && x.right() == null;
        }

        public int maxPathSum(TreeNode x) {
            if (isLeaf(x)) {
                return x.val;
            }
            populateSum(x.left());
            populateSum(x.right());
            if (x.right() == null) {
                subtrees.add(findMax(x.val, x.left().val, x.val + x.left().val));
            } else if (x.left() == null) {
                subtrees.add(findMax(x.val, x.right().val, x.val + x.right().val));
            } else {
                subtrees.add(findMax(x.val, x.left().val, x.right().val,
                        x.val + x.left().val, x.val + x.right().val, x.val + x.left().val + x.right().val));
            }
            int[] ansArr = new int[subtrees.size()];
            int ansArrPtr = 0;
            for (int answer : subtrees) {
                ansArr[ansArrPtr++] = answer;
            }
            return findMax(ansArr);
        }

        private void populateSum(TreeNode x) {
            if (x == null) {
                return;
            }
            populateSum(x.left());
            populateSum(x.right());
            if (isLeaf(x)) {
                subtrees.add(x.val);
                return;
            }
            if (x.right() == null) {
                x.val = findMax(x.val, x.val + x.left().val);
                if (x.left().val > x.val) {
                    subtrees.add(x.left().val);
                }
                return;
            }
            if (x.left() == null) {
                x.val = findMax(x.val, x.val + x.right().val);
                if (x.right().val > x.val) {
                    subtrees.add(x.right().val);
                }
                return;
            }
            int subm = x.val + x.left().val + x.right().val;
            int subl = x.left().val;
            int subr = x.right().val;
            x.val = findMax(x.val, x.val + x.left().val, x.val + x.right().val);
            if (subl > x.val) {
                subtrees.add(subl);
            }
            if (subr > x.val) {
                subtrees.add(subr);
            }
            if (subm > x.val) {
                subtrees.add(subm);
            }
        }

        private int findMax(int... arr) {
            int result = Integer.MIN_VALUE;
            for (int x : arr) {
                if (x > result) {
                    result = x;
                }
            }
            return result;
        }
    }

    private void compute() {
        Tree tree = new Tree(13);
        //int[] input = {5, 4, 8, 11, MIN, 13, 4, 7, 2, MIN, MIN, MIN, 1};
        //int[] input = {8,9,-6,MIN,MIN,5,9};
        //int[] input = {1, -2, -3, 1, 3, -2, MIN, -1};
        //int[] input = {-8, -9, -2, MIN, MIN, 3, MIN, MIN, 8};
        //int[] input = {1, MIN, -7, -9, -8, MIN, MIN, 3, MIN, MIN, -2};
        int[] input = {-3, -4, 0, MIN, MIN, 0, 1, MIN, 7, MIN, -3};
        for (int x : input) {
            tree.add(x);
        }
        System.out.println(tree.maxPathSum(tree.root()));

        //for(int i=1; i<tree.topIdx; i++) {
        //String output = tree.arr[i]==null ? i+" ":i+" "+tree.arr[i].toString();
        //System.out.println(output);
        //}
    }
}
