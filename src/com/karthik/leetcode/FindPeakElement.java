/*
 * LeetCode Problem: https://leetcode.com/problems/find-peak-element/#/description
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindPeakElement {

    public int findPeakElement(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int n = a.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int m = (lo + hi) >> 1;
            if (m == 0 && a[m] > a[m + 1]) {
                return m;
            } else if (m == n - 1 && a[m] > a[m - 1]) {
                return m;
            } else if (m > 0 && m < n - 1 && a[m - 1] < a[m] && a[m] > a[m + 1]) {
                return m;
            } else if (m == 0 || m > 0 && m < n - 1 && a[m - 1] < a[m] && a[m] < a[m + 1]) {
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }

        return lo;
    }
}
