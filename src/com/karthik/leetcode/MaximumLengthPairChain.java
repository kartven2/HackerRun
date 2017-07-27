/*
 * Leetcode: https://leetcode.com/problems/maximum-length-of-pair-chain/tabs/description
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximumLengthPairChain {

    public int findLongestChain(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int n = a.length;
        Arrays.sort(a, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        int[] lis = new int[n];
        int k = 0;
        for (int i = 1; i < n; i++) {
            if (a[i][0] == a[i - 1][0]) {
                continue;
            } else if (a[i][0] > a[lis[k]][1]) {
                lis[++k] = i;
            } else if (a[i][1] < a[lis[k]][1]) {
                lis[k] = i;
            }
        }
        return k + 1;
    }
}
