/*
 * Leetcode: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements.
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RandomizedCollection {

    private Map<Integer, Integer> m1;
    private Map<Integer, Set<Integer>> m2;
    private int idx;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        m1 = new HashMap<>();
        m2 = new HashMap<>();
        idx = 0;
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not
     * already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> set = m2.get(val);
        boolean result = set == null;
        if (result) {
            set = new HashSet<>();
        }
        set.add(idx);
        m1.put(idx++, val);
        m2.put(val, set);
        return result;
    }

    /**
     * Removes a value from the collection. Returns true if the collection
     * contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> pos = m2.get(val);
        if (pos == null) {
            return false;
        }
        int lastVal = m1.get(idx - 1);
        if (lastVal == val) {
            m1.remove(idx - 1);
            pos.remove(--idx);
            if (pos.isEmpty()) {
                m2.remove(val);
            }
            return true;
        }
        m1.remove(idx - 1);
        Set<Integer> posLastVal = m2.get(lastVal);
        posLastVal.remove(--idx);
        int posVal = pos.iterator().next();
        pos.remove(posVal);
        if (pos.isEmpty()) {
            m2.remove(val);
        }
        posLastVal.add(posVal);
        m1.put(posVal, lastVal);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int randIdx = (int) (Math.random() * idx);
        return m1.get(randIdx);
    }
}
