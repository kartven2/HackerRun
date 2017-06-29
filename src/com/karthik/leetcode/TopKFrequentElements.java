/*
 * LeetCode problem : https://leetcode.com/problems/top-k-frequent-elements/#/description
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note: 
 * You may assume k is always valid.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            Integer y = map.get(x);
            y = y == null ? 1 : (y + 1);
            map.put(x, y);
        }
        List<Integer>[] list = (List<Integer>[]) new LinkedList[n + 1];
        for (int x : map.keySet()) {
            int freq = map.get(x);
            if (list[freq] == null) {
                list[freq] = new LinkedList<>();
            }
            list[freq].add(x);
        }
        for (int i = n; i > 0 && result.size() < k; i--) {
            if (list[i] != null) {
                result.addAll(list[i]);
            }
        }
        return result;
    }
}
