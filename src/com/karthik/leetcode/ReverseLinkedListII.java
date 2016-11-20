/*
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Reverse a linked list from position m to n.
 * Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReverseLinkedListII {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode first = null, second = null, fp = null, sn = null;

    private void reverse() {
        second.next = null;
        ListNode rev = null, tail = first;
        while (first != null) {
            ListNode temp = first.next;
            first.next = rev;
            rev = first;
            first = temp;
        }
        first = rev;
        second = tail;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        first = second = head;
        for (int i = 0; i < n - m; i++) {
            second = second.next;
        }
        for (int i = 1; i < m; i++) {
            fp = first;
            first = first.next;
            second = second.next;
        }
        sn = second.next;
        reverse();
        if (fp != null) {
            fp.next = first;
        }
        second.next = sn;
        return (fp != null) ? head : first;
    }
}
