/*
 * LeetCode: https://leetcode.com/problems/subarray-sum-equals-k/#/description
 * 
 * Given an array of integers and an integer k, you need to
 * find the total number of continuous subarrays whose sum equals to k.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] a, int k) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += a[i];
            Integer val = map.get(sum - k);
            if (val != null) {
                count += val;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
