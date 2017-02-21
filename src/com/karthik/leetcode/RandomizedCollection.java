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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RandomizedCollection {

    private List<Integer> list;
    private Map<Integer, List<Integer>> map;
    private int idx=0;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        list = new LinkedList<>();
        map = new HashMap<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        List<Integer> posList = map.get(val);
        boolean result = posList == null;
        if(result) {
            posList = new LinkedList<>();
        }
        list.add(val);
        posList.add(idx++);
        map.put(val, posList);
        return result;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
      List<Integer> posList = map.get(val);
      boolean result = posList != null;
      
        
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {

    }
}
