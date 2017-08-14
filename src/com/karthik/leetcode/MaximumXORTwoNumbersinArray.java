/*
 * LeetCode: https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximumXORTwoNumbersinArray {

    public int findMaximumXOR(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int max = 0, mask = 0, n = a.length;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int x : a) {
                set.add(x & mask);
            }
            int tmp = max | (1 << i);
            for (int x : set) {
                if (set.contains(tmp ^ x)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}
