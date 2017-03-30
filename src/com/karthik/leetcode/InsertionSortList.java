/*
 * Leetcode: https://leetcode.com/problems/insertion-sort-list/#/description
 *
 * Sort a linked list using insertion sort.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class InsertionSortList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            while (pre.next != null && pre.next.val < curr.val) {
                pre = pre.next;
            }
            curr.next = pre.next;
            pre.next = curr;
            pre = dummy;
            curr = next;
        }

        return dummy.next;
    }

}
