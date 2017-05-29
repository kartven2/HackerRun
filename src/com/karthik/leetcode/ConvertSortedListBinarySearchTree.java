/*
 * LeetCode: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/#/description
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConvertSortedListBinarySearchTree {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode x = head;
        int n = 0;
        while (x != null) {
            x = x.next;
            n++;
        }
        this.head = head;
        TreeNode root = build(n);
        return root;
    }

    private TreeNode build(int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode left = build(n / 2);
        TreeNode root = new TreeNode(head.val);
        root.left = left;
        head = head.next;
        TreeNode right = build(n - (n / 2) - 1);
        root.right = right;
        return root;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        TreeNode root = build(head);
        return root;
    }

    private TreeNode build(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode x = head, mid = head, prev = null;
        int count = 0;
        while (x != null) {
            if ((count & 1) == 1) {
                prev = mid;
                mid = mid.next;
            }
            count++;
            x = x.next;
        }
        TreeNode v = new TreeNode(mid.val);
        if (prev != null) {
            prev.next = null;
        }
        if (head != mid) {
            v.left = build(head);
        }
        v.right = build(mid.next);
        return v;
    }
}
