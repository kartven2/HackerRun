/*
 * Leetcode: https://leetcode.com/problems/rotate-list/
 *
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RotateList {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int n = 1;
        while (p.next != null) {
            p = p.next;
            n++;
        }
        if (n <= k) {
            k %= n;
        }
        if (k == 0) {
            return head;
        }
        p = head;
        ListNode q = head;
        int j = 0;
        while (j < k) {
            q = q.next;
            j++;
        }
        while (q.next != null) {
            q = q.next;
            p = p.next;
        }
        ListNode temp = p.next;
        p.next = null;
        q.next = head;
        head = temp;
        return head;
    }
}
