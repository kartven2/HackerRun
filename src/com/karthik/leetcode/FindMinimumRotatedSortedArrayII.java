/*
 * LeetCode: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/#/description
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindMinimumRotatedSortedArrayII {

    public int findMin(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        return search(a, 0, a.length - 1);
    }

    private int search(int[] a, int lo, int hi) {
        if (hi - lo < 10) {
            int min = a[lo];
            for (int i = lo + 1; i <= hi; i++) {
                if (a[i] < min) {
                    min = a[i];
                }
            }
            return min;
        }
        if (a[lo] < a[hi]) {
            return a[lo];
        }
        int mid = (lo + hi) / 2;
        if (a[mid - 1] > a[mid] && a[mid] <= a[mid + 1]) {
            return a[mid];
        }
        if (a[lo] == a[mid] && a[mid] == a[hi]) {
            return Math.min(search(a, lo, mid - 1), search(a, mid + 1, hi - 1));
        }
        if (a[lo] == a[mid]) {
            return search(a, mid + 1, hi);
        }
        if (a[mid] == a[hi]) {
            return search(a, lo, mid - 1);
        }
        if (a[mid] > a[hi]) {
            return search(a, mid + 1, hi);
        }
        return search(a, lo, mid - 1);
    }
}
