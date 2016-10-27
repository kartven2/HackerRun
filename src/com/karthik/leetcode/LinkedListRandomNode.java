/*
 * LeetCode: https://leetcode.com/problems/linked-list-random-node/
 *
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LinkedListRandomNode {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode head;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode x = head;
        int i = 1, result = x.val;
        while (x.next != null) {
            x = x.next;
            int j = (int) (Math.random() * (i + 1));
            if (j == 0) {
                result = x.val;
            }
            i++;
        }
        return result;
    }
}
