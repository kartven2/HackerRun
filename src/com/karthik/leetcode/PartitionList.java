/*
 * Leetcode: https://leetcode.com/problems/partition-list/#/description
 * Given a linked list and a value x, partition it such that all nodes
 * less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes
 * in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PartitionList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode curr = head, sx = small, lx = large;
        ListNode next = null;
        while (curr != null) {
            while (curr != null && curr.val < x) {
                next = curr.next;
                curr.next = null;
                sx.next = curr;
                sx = sx.next;
                curr = next;
            }
            while (curr != null && curr.val >= x) {
                next = curr.next;
                curr.next = null;
                lx.next = curr;
                lx = lx.next;
                curr = next;
            }
        }
        if (sx == small) {
            return large.next;
        } else if (lx == large) {
            return small.next;
        }
        sx.next = large.next;
        return small.next;
    }
}
