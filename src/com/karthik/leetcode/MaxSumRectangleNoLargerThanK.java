/*-
 * LeetCode Problem: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/#/description
 */
package com.karthik.leetcode;

import java.util.TreeSet;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaxSumRectangleNoLargerThanK {

    public int maxSumSubmatrix(int[][] a, int val) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int r = a.length, c = a[0].length, ans = Integer.MIN_VALUE;
        boolean isRowGreater = r > c;
        int n = isRowGreater ? r : c, m = isRowGreater ? c : r;
        for (int i = 0; i < m; i++) {
            int[] arr = new int[n];
            for (int j = i; j >= 0; j--) {
                for (int k = 0; k < n; k++) {
                    arr[k] += isRowGreater ? a[k][j] : a[j][k];
                }
                ans = Math.max(ans, getNumberCloseToVal(arr, val));
            }
        }
        return ans;
    }

    private int getNumberCloseToVal(int[] a, int val) {
        int result = Integer.MIN_VALUE, sum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            Integer ceil = set.ceiling(sum - val);
            if (ceil != null) {
                result = Math.max(result, sum - ceil);
            }
            set.add(sum);
        }
        return result;
    }
}
