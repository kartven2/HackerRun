/*
 * LeetCode: https://leetcode.com/problems/swap-nodes-in-pairs/#/description
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space.
 * You may not modify the values in the list, only nodes itself can be changed.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SwapNodesInPairs {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode c = dummy;
        while (c.next != null && c.next.next != null) {
            ListNode first = c.next;
            ListNode second = c.next.next;
            first.next = second.next;
            second.next = first;
            c.next = second;
            c = c.next.next;
        }
        return dummy.next;
    }

    public static void main(String... args) {
        SwapNodesInPairs swp = new SwapNodesInPairs();
        ListNode x = new ListNode(1);
        x.next = new ListNode(2);
        x.next.next = new ListNode(3);
        x.next.next.next = new ListNode(4);
        swp.swapPairs(x);

    }

    public ListNode swapPairs(ListNode x) {
        if (x == null || x.next == null) {
            return x;
        }
        ListNode y = swapPairs(x.next.next);
        ListNode first = x;
        ListNode second = x.next;
        first.next = y;
        second.next = first;
        x = second;
        return x;
    }
}
