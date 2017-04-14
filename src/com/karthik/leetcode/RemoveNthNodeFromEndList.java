/*
 * LeetCode Problem: https://leetcode.com/problems/remove-nth-node-from-end-of-list/#/description
 *
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveNthNodeFromEndList {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return head;
        }
        ListNode a = head, b = head, pa = null;
        int x = n - 1;
        while (x > 0 && b.next != null) {
            b = b.next;
            x--;
        }
        while (b.next != null) {
            pa = a;
            a = a.next;
            b = b.next;
        }
        if (pa != null) {
            pa.next = a.next;
        }
        if (head == a) {
            head = a.next;
        } else {
            a = null;
        }
        return head;
    }
}
