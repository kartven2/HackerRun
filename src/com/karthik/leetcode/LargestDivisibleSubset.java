/*
 * LeetCode: https://leetcode.com/problems/largest-divisible-subset/#/description
 * Given a set of distinct positive integers,
 * find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int n = a.length;
        Arrays.sort(a);
        int[] p = new int[n];
        int[] l = new int[n];
        int maxIdx = -1, maxLen = -1;
        for (int i = 0; i < n; i++) {
            if (isPrime(a[i])) {
                if (a[0] == 1 && i > 0) {
                    p[i] = 0;
                    l[i] = l[0] + 1;
                } else {
                    p[i] = i;
                    l[i] = 1;
                }
            } else {
                boolean foundParent = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (a[i] % a[j] == 0) {
                        if (1 + l[j] > l[i]) {
                            foundParent = true;
                            l[i] = l[j] + 1;
                            p[i] = j;
                        }
                    }
                }
                if (!foundParent) {
                    l[i] = 1;
                    p[i] = i;
                }
            }
            if (l[i] > maxLen) {
                maxLen = l[i];
                maxIdx = i;
            }
        }
        int x = maxIdx;
        while (x != p[x]) {
            result.add(a[x]);
            x = p[x];
        }
        result.add(a[x]);
        return result;
    }
}
