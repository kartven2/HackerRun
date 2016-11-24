/*
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveDuplicatesFromSortedListII {

    private static final int MIN = Integer.MIN_VALUE;

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dh = new ListNode(MIN);
        dh.next = head;
        ListNode p = dh, c = head, n = head.next;
        while (n != null) {
            boolean skip = false;
            while (n != null && c.val == n.val) {
                n = n.next;
                c = c.next;
                skip = true;
            }
            if (n == null) {
                p.next = null;
                return dh.next;
            }
            if (skip) {
                p.next = n;
                c = n;
            } else {
                p = p.next;
                c = c.next;
            }
            n = n.next;
        }
        return dh.next;
    }
}
