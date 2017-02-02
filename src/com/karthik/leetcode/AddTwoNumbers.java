/*
 * LeetCode: https://leetcode.com/problems/add-two-numbers/
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AddTwoNumbers {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r = new ListNode(Integer.MIN_VALUE);
        if (l1 == null && l2 == null) {
            return l1;
        }
        int carry = 0;
        ListNode x = r;
        while (l1 != null || l2 != null) {
            int sum = l1 != null && l2 != null ? l1.val + l2.val + carry
                    : l1 == null ? l2.val + carry
                            : l2 == null ? l1.val + carry : carry;
            x.next = new ListNode(sum % 10);
            x = x.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            carry = sum / 10;
        }
        if (carry > 0) {
            x.next = new ListNode(carry);
        }
        return r.next;
    }
}
