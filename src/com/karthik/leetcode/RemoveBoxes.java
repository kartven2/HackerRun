/*
 * LeetCode Problem: https://leetcode.com/problems/remove-boxes/#/description
 *
 * Given several boxes with different colors represented by different positive numbers. 
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveBoxes {

    public int removeBoxes(int[] b) {
        if (b == null || b.length == 0) {
            return 0;
        }
        int n = b.length;
        int[][][] dp = new int[n][n][n];
        return dfs(dp, 0, n - 1, 0, b);
    }

    private int dfs(int[][][] dp, int i, int j, int k, int[] b) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j][k] > 0) {
            return dp[i][j][k];
        }
        //deleting 1 box now
        int res = (k + 1) * (k + 1) + dfs(dp, i + 1, j, 0, b);
        for (int m = i + 1; m <= j; m++) {
            if (b[i] == b[m]) {
                //Search the max for deleting multiple intermediate boxes and joining two boxes b[m] and b[i]
                res = Math.max(res, dfs(dp, i + 1, m - 1, 0, b) + dfs(dp, m, j, k + 1, b));
            }
        }
        dp[i][j][k] = res;
        return res;
    }
}
