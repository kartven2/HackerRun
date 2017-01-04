/*
 * LeetCode Problem: https://leetcode.com/problems/reorder-list/
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * Given {1,2,3,4}, reorder it to {1,4,2,3}
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReorderList {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode findMiddle(ListNode x) {
        ListNode p1 = x, prev = null, p2 = x;
        while (p2.next != null && p2.next.next != null) {
            prev = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        if (p2.next != null) {
            ListNode tmp = p1.next;
            p1.next = null;
            return tmp;
        }
        if (prev != null) {
            prev.next = null;
        }
        return p1;
    }

    private ListNode reverse(ListNode x) {
        if (x == null || x.next == null) {
            return x;
        }
        ListNode rest = null;
        while (x != null) {
            ListNode temp = x.next;
            x.next = rest;
            rest = x;
            x = temp;
        }
        return rest;
    }

    private void merge(ListNode x, ListNode y) {
        ListNode ax = x.next, ay = y, cx = x;
        boolean pthis = true;
        while (true) {
            if (ax == null && ay == null) {
                return;
            } else if (ax == null || pthis) {
                cx.next = ay;
                ay = ay.next;
                pthis = !pthis;
                cx = cx.next;
            } else if (ay == null || !pthis) {
                cx.next = ax;
                ax = ax.next;
                pthis = !pthis;
                cx = cx.next;
            }
        }
    }

    public void reorderList(ListNode head) {
        if (head == null
                || head.next == null
                || head.next.next == null) {
            return;
        }
        ListNode middle = findMiddle(head);
        ListNode head2 = reverse(middle);
        merge(head, head2);
    }
}
