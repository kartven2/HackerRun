/*
 * LeetCode Problem: https://leetcode.com/problems/partition-equal-subset-sum/#/description
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PartitionEqualSubsetSum {

    public static void main(String... args) {
        PartitionEqualSubsetSum pes = new PartitionEqualSubsetSum();
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        pes.canPartition(a);
    }

    public boolean canPartition(int[] a) {
        if (a == null || a.length == 0) {
            return false;
        }
        int n = a.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= a[i]; j--) {
                dp[j] = dp[j] | dp[j - a[i]];
                if (dp[sum]) {
                    return true;
                }
            }
        }
        return dp[sum];
    }
}
