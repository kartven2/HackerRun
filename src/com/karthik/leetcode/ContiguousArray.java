/*
 * LeetCode: https://leetcode.com/problems/contiguous-array/#/description
 * Given a binary array, find the maximum length of a contiguous subarray
 * with equal number of 0 and 1.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ContiguousArray {

    public int findMaxLength(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, cnt = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            cnt = a[i] == 1 ? cnt + 1 : cnt - 1;
            Integer x = map.get(cnt);
            if (x != null) {
                if (max < i - x) {
                    max = i - x;
                }
            } else {
                map.put(cnt, i);
            }
        }
        return max;
    }
}
