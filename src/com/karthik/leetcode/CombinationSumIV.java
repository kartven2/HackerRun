/*
 * LeetCode: https://leetcode.com/problems/combination-sum-iv/#/description
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CombinationSumIV {

    public static void main(String... args) {
        CombinationSumIV cs4 = new CombinationSumIV();
        System.out.println(cs4.combinationSum4(new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}, 10));
    }

    public int combinationSum4(int[] a, int k) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        Arrays.sort(a);
        if (k < a[0]) {
            return 0;
        }
        int lo = a[0], hi = k, m = hi - lo + 1;
        int[] dp = new int[m];
        for (int i = lo, j = 0; i <= hi; i++) {
            int idx = i - lo;
            if (j < n && a[j] == i) {
                j++;
                dp[idx] = 1;
            }
            for (int p = 0; p < n && a[p] < i; p++) {
                if (i - a[p] - lo >= 0) {
                    int jdx = i - a[p] - lo;
                    dp[idx] += dp[jdx];
                }
            }
        }
        return dp[hi - lo];
    }
}
