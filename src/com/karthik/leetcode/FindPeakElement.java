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

    public int findPeakElement3(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int n = a.length;
        int[] b = new int[n + 2];
        b[0] = Integer.MIN_VALUE;
        b[b.length - 1] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            b[i + 1] = a[i];
        }
        for (int i = 1; i < b.length - 1; i++) {
            if (b[i] > b[i - 1] && b[i] > b[i + 1]) {
                return i - 1;
            }
        }
        return 0;
    }

    public int findPeakElement2(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int n = a.length;
        if (n == 1 || a[0] > a[1]) {
            return 0;
        }
        if (a[n - 1] > a[n - 2]) {
            return n - 1;
        }
        int lo = 1, hi = n - 2;
        while (lo <= hi) {
            int m = (lo + hi) >> 1;
            if (a[m] > a[m - 1] && a[m] > a[m + 1]) {
                return m;
            } else if (a[m - 1] < a[m] && a[m] < a[m + 1]) {
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }
        return lo;
    }

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
