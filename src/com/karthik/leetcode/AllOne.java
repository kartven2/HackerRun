/*
 * LeetCode Problem: https://leetcode.com/problems/all-oone-data-structure/
 * Implement a data structure supporting the following operations:
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
 * If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AllOne {

    class Node {

        private long id;
        private Set<String> keys;
        private Node prev, next;

        Node(long val) {
            id = val;
        }
    }

    private Node first, last;
    private Map<String, Long> keyCountMap;
    private Map<Long, Node> countNodeMap;

    public AllOne() {
        first = new Node(Long.MIN_VALUE);
        last = new Node(Long.MAX_VALUE);
        first.next = last;
        last.prev = first;
        keyCountMap = new HashMap<>();
        countNodeMap = new HashMap<>();
    }

    public String getMinKey() {
        return first.next == last ? "" : first.next.keys.iterator().next();
    }

    public String getMaxKey() {
        return last.prev == first ? "" : last.prev.keys.iterator().next();
    }

    public void inc(String key) {
        if (keyCountMap.containsKey(key)) {
            long idx = keyCountMap.get(key);
            keyCountMap.put(key, idx + 1);
            Node currNode = countNodeMap.get(idx);
            currNode.keys.remove(key);
            Node prev = currNode.prev;
            Node next = currNode.next;
            if (currNode.keys.isEmpty()) {
                prev.next = next;
                next.prev = prev;
                countNodeMap.remove(idx);
            } else {
                prev = currNode;
            }
            currNode = countNodeMap.get(idx + 1);
            if (currNode == null) {
                currNode = new Node(idx + 1);
                currNode.keys = new LinkedHashSet<>();
                currNode.keys.add(key);
                prev.next = currNode;
                currNode.prev = prev;
                currNode.next = next;
                next.prev = currNode;
                countNodeMap.put(idx + 1, currNode);
            } else {
                currNode.keys.add(key);
            }
            return;
        }
        keyCountMap.put(key, 1L);
        Node currNode = countNodeMap.get(1L);
        if (currNode == null) {
            currNode = new Node(1L);
            currNode.keys = new LinkedHashSet<>();
            currNode.keys.add(key);
            Node next = first.next;
            first.next = currNode;
            currNode.prev = first;
            currNode.next = next;
            next.prev = currNode;
            countNodeMap.put(1L, currNode);
        } else {
            currNode.keys.add(key);
        }
    }

    public void dec(String key) {
        if (keyCountMap.containsKey(key)) {
            long idx = keyCountMap.get(key);
            if (idx == 1) {
                keyCountMap.remove(key);
            } else {
                keyCountMap.put(key, idx - 1);
            }
            Node currNode = countNodeMap.get(idx);
            currNode.keys.remove(key);
            Node prev = currNode.prev;
            Node next = currNode.next;
            if (currNode.keys.isEmpty()) {
                prev.next = next;
                next.prev = prev;
                countNodeMap.remove(idx);
            } else {
                prev = currNode;
            }
            if (idx > 1) {
                currNode = countNodeMap.get(idx - 1);
                if (currNode == null) {
                    currNode = new Node(idx - 1);
                    currNode.keys = new LinkedHashSet<>();
                    currNode.keys.add(key);
                    prev.next = currNode;
                    currNode.prev = prev;
                    currNode.next = next;
                    next.prev = currNode;
                    countNodeMap.put(idx - 1, currNode);
                } else {
                    currNode.keys.add(key);
                }
            }
        }
    }
}
