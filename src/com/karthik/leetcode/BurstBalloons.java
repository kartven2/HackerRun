/*
 * Leetcode: https://leetcode.com/problems/burst-balloons/
 *
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i.
 * After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BurstBalloons {

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] a = new int[n + 2];
        a[0] = a[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            a[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        return burstBallon(1, n, a, dp);
    }

    private int burstBallon(int i, int j, int[] a, int[][] dp) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        for (int k = i; k <= j; k++) {
            dp[i][j] = Math.max(dp[i][j], burstBallon(i, k - 1, a, dp) + a[i - 1] * a[k] * a[j + 1] + burstBallon(k + 1, j, a, dp));
        }
        return dp[i][j];
    }
}
