/*
 * LeetCode: https://leetcode.com/problems/largest-divisible-subset/#/description
 * Given a set of distinct positive integers,
 * find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LargestDivisibleSubset {

    private boolean isPrime(int x) {
        if (x < 4) {
            return true;
        }
        if (x % 2 == 0 || x % 3 == 0) {
            return false;
        }
        int i = 5, w = 2;
        while (i * i <= x) {
            if (x % i == 0) {
                return false;
            }
            i += w;
            w = 6 - w;
        }
        return true;
    }

    public List<Integer> largestDivisibleSubset(int[] a) {
        List<Integer> result = new ArrayList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        if (a.length == 1) {
            result.add(a[0]);
            return result;
        }
        int n = a.length, max = -1;
    }
}
