/*-
 * Leetcode: https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReverseNodesInGroup {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Tuple {

        ListNode head;
        ListNode tail;

        Tuple(ListNode h, ListNode t) {
            head = h;
            tail = t;
        }
    }

    private Tuple reverse(ListNode first, ListNode last) {
        if (first.next == last) {
            last.next = first;
            first.next = null;
            return new Tuple(last, first);
        }
        ListNode temp = first, rest = null;
        while (temp.next != last) {
            ListNode x = temp.next;
            temp.next = rest;
            rest = temp;
            temp = x;
        }
        last.next = temp;
        temp.next = rest;
        first.next = null;
        return new Tuple(last, first);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode lp1 = head, lp2 = head;
        head = reverseGroup(lp1, lp2, k);
        return head;
    }

    private ListNode reverseGroup(ListNode lp1, ListNode lp2, int k) {
        if (lp2 == null) {
            return null;
        }
        int i = 1;
        for (; lp2.next != null && i < k; i++, lp2 = lp2.next);
        if (i < k) {
            return lp1;
        }
        ListNode suffix = reverseGroup(lp2.next, lp2.next, k);
        Tuple result = reverse(lp1, lp2);
        result.tail.next = suffix;
        return result.head;
    }
}
