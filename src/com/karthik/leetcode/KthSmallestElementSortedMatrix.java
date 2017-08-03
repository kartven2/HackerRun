/*-
 * LeetCode Problem: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KthSmallestElementSortedMatrix {

    public int kthSmallest(int[][] a, int k) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, lo = a[0][0], hi = a[n - 1][n - 1];
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            int count = getCount(a, mid);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    private int getCount(int[][] a, int mid) {
        int n = a.length, i = n - 1, j = 0, count = 0;
        while (i >= 0 && j < n) {
            if (a[i][j] <= mid) {
                count += (i + 1);
                j++;
            } else {
                i--;
            }
        }
        return count;
    }
}
