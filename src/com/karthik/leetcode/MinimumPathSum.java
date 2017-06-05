/*
 * LeetCode Problem: https://leetcode.com/problems/minimum-path-sum/#/description
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumPathSum {

    public int minPathSum2(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j != 0) {
                    a[i][j] += a[i][j - 1];
                } else if (i != 0 && j == 0) {
                    a[i][j] += a[i - 1][j];
                } else if (i > 0 && j > 0) {
                    int val = a[i - 1][j] < a[i][j - 1] ? a[i - 1][j] : a[i][j - 1];
                    a[i][j] += val;
                }
            }
        }
        return a[n - 1][m - 1];
    }

    public int minPathSum(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length;
        Integer[][] dp = new Integer[n][m];
        return walk(a, 0, 0, n, m, 0, dp);
    }

    private int walk(int[][] a, int i, int j, int n, int m, int sum, Integer[][] dp) {
        if (i == n - 1 && j == m - 1) {
            return sum + a[i][j];
        }
        if (dp[i][j] != null) {
            return dp[i][j] + sum;
        }
        int right = Integer.MAX_VALUE, down = Integer.MAX_VALUE;
        if (j + 1 < m) {
            right = walk(a, i, j + 1, n, m, sum + a[i][j], dp);
        }
        if (i + 1 < n) {
            down = walk(a, i + 1, j, n, m, sum + a[i][j], dp);
        }
        int val = right < down ? right : down;
        dp[i][j] = val - sum;
        return val;
    }
}
