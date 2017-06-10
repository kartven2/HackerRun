/*
 * LeetCode: https://leetcode.com/problems/increasing-triplet-subsequence/#/description
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k 
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 *
 * Given [5, 4, 3, 2, 1],
 * return false.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] a) {
        if (a == null || a.length < 3) {
            return false;
        }
        int l = Integer.MAX_VALUE, m = Integer.MAX_VALUE, r = 0, n = a.length;
        for (; r < n; r++) {
            if (a[r] <= l) {
                l = a[r];
            } else if (a[r] <= m) {
                m = a[r];
            } else {
                return true;
            }
        }
        return false;
    }
}
