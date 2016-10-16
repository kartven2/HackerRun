/*
 * LeetCode: https://leetcode.com/problems/merge-k-sorted-lists/
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MergeKSortedLists {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int n = lists.length;
        return mergeKLists(lists, 0, n - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + hi >> 1;
            return merge(mergeKLists(lists, lo, mid), mergeKLists(lists, mid + 1, hi));
        }
        return lists[lo];
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode res = new ListNode(0);
        ListNode first = res;
        while (a != null && b != null) {
            if (a.val < b.val) {
                res.next = new ListNode(a.val);
                a = a.next;
            } else {
                res.next = new ListNode(b.val);
                b = b.next;
            }
            res = res.next;
        }
        if (a != null) {
            res.next = a;
        }
        if (b != null) {
            res.next = b;
        }
        return first.next;
    }
}
