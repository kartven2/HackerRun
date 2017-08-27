/*-
 * LeetCode Problem: https://leetcode.com/problems/add-two-numbers-ii/description/
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AddTwoNumbersII {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode a = l1, b = l2;
        int n1 = 0, n2 = 0;
        while (a != null) {
            a = a.next;
            n1++;
        }
        while (b != null) {
            b = b.next;
            n2++;
        }
        int diff = Math.abs(n1 - n2);
        ListNode res = n1 < n2 ? add(l2, l1, null, diff) : add(l1, l2, null, diff);
        ListNode ans = res;
        if (res.val >= 10) {
            ans = new ListNode(res.val / 10);
            ans.next = res;
        }
        while (res != null) {
            res.val %= 10;
            res = res.next;
        }
        return ans;
    }

    private ListNode add(ListNode a, ListNode b, ListNode x, int diff) {
        if (a == null && b == null) {
            return null;
        }
        if (x == null) {
            x = new ListNode(-1);
        }
        if (diff > 0) {
            x.next = add(a.next, b, x.next, diff - 1);
            x.val = a.val;
            if (x.next != null) {
                x.val += (x.next.val / 10);
            }
        } else {
            x.next = add(a.next, b.next, x.next, diff);
            x.val = a.val + b.val;
            if (x.next != null) {
                x.val += (x.next.val / 10);
            }
        }
        return x;
    }

}
