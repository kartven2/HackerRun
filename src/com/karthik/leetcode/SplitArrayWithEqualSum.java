/*
 * LeetCode: https://leetcode.com/problems/split-array-with-equal-sum/description/
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SplitArrayWithEqualSum {

    public boolean splitArray(int[] a) {
        if (a == null || a.length < 7) {
            return false;
        }
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] += (sum[i - 1] + a[i]);
        }
        for (int j = 3; j < a.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i]) {
                    set.add(sum[i - 1]);
                }
            }
            for (int k = j + 2; k < n - 1; k++) {
                if ((sum[k - 1] - sum[j] == sum[n - 1] - sum[k]) && set.contains(sum[k - 1] - sum[j])) {
                    return true;
                }
            }
        }
        return false;
    }
}
