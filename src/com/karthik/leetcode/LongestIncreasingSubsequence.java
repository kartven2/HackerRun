/*
 * Leetcode:https://leetcode.com/problems/longest-increasing-subsequence/#/description
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101],
 * therefore the length is 4. Note that there may be more than
 * one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, j = 0;
        int[] p = new int[n];
        int[] lis = new int[n];
        lis[0] = 0;
        p[0] = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] > a[lis[j]]) {
                j++;
                lis[j] = i;
                p[i] = lis[j - 1];
            } else {
                int lo = 0, hi = j;
                while (lo <= hi) {
                    int mid = lo + ((hi - lo) >> 1);
                    if (a[lis[mid]] == a[i]) {
                        lo = mid;
                        break;
                    } else if (a[lis[mid]] > a[i]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                lis[lo] = i;
                p[i] = lo == 0 ? i : lis[lo - 1];
            }
        }
        int pos = lis[j];
        int count = 1;
        while (p[pos] != pos) {
            pos = p[pos];
            count++;
        }
        return count;
    }
}
