/*
 * LeetCode: https://leetcode.com/problems/copy-list-with-random-pointer/
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list..
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CopyListRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode x = head;
        while (x != null) {
            map.put(x, new RandomListNode(x.label));
            x = x.next;
        }
        x = head;
        while (x != null) {
            if (x.next != null) {
                map.get(x).next = map.get(x.next);
            }
            x = x.next;
        }
        x = head;
        while (x != null) {
            if (x.random != null) {
                map.get(x).random = map.get(x.random);
            }
            x = x.next;
        }
        x = map.get(head);
        return x;
    }
}

class RandomListNode {

    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
};
