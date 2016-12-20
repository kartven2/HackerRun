/*
 * https://leetcode.com/problems/sort-list/
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SortList {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode getMid(ListNode x) {
        if (x == null || x.next == null) {
            return x;
        }
        ListNode p1 = x, p2 = x;
        while (p2 != null && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    private ListNode merge(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode temp = null;
        if (b.val < a.val) {
            temp = b;
            temp.next = merge(a, b.next);
        } else {
            temp = a;
            temp.next = merge(a.next, b);
        }
        return temp;
    }

    public ListNode sortList(ListNode x) {
        if (x == null || x.next == null) {
            return x;
        }
        ListNode mid = getMid(x);
        ListNode midNext = mid.next;
        mid.next = null;
        return merge(sortList(x), sortList(midNext));
    }
}
