/*
 * LeetCode: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/#/description
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int lo = 0, hi = a.length - 1, mid = 0;
        while (lo < hi) {
            mid = (lo + hi) >> 1;
            if (a[mid] > a[hi]) {
                lo = mid + 1;
            } else if (a[lo] < a[mid]) {
                hi = mid - 1;
            } else if (a[mid] < a[hi]) {
                hi = mid;
            } else {
                return a[mid];
            }
        }
        return a[lo];
    }
}
