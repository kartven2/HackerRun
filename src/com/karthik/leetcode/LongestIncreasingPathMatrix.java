/*
 * Leetcode: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/#/description
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestIncreasingPathMatrix {

    public static void main(String... args) {
        int[][] a = {{12, 80, 94, 77}, {13, 108, 96, 77}, {110, 109, 114, 77}, {111, 112, 113, 77}};
        LongestIncreasingPathMatrix b = new LongestIncreasingPathMatrix();
        b.longestIncreasingPath(a);
    }

    public int longestIncreasingPath(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int x = a.length, y = a[0].length;
        int dp[][] = new int[x][y];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                ans = Math.max(ans, srch(a, i, j, x, y, Integer.MIN_VALUE, dp));
            }
        }
        return ans;
    }

    private int srch(int[][] a, int i, int j, int n, int m, int pre, int[][] dp) {
        if (i < 0 || i >= n || j < 0 || j >= m || a[i][j] <= pre) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int max = 0;
        max = Math.max(max, srch(a, i + 1, j, n, m, a[i][j], dp));
        max = Math.max(max, srch(a, i - 1, j, n, m, a[i][j], dp));
        max = Math.max(max, srch(a, i, j + 1, n, m, a[i][j], dp));
        max = Math.max(max, srch(a, i, j - 1, n, m, a[i][j], dp));
        max++;
        dp[i][j] = max;
        return max;
    }

    private int n = 0;
    private int m = 0;
    private int[][] a = null;

    public int longestIncreasingPath2(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        a = arr;
        n = a.length;
        m = a[0].length;
        int[][][] dp = new int[n][m][4];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, dp, new boolean[n][m]));
            }
        }
        return ans;
    }

    private boolean canVisit(int i, int j, int curVal, boolean[][] mark) {
        return i >= 0 && i < n && j >= 0 && j < m && !mark[i][j] && curVal < a[i][j];
    }

    private int dfs(int i, int j, int[][][] dp, boolean[][] mark) {
        int max = 1;
        mark[i][j] = true;
        if (canVisit(i, j + 1, a[i][j], mark)) {
            if (dp[i][j][0] == 0) {
                dp[i][j][0] = 1 + dfs(i, j + 1, dp, mark); //right
            }
            max = Math.max(max, dp[i][j][0]);
        }
        if (canVisit(i, j - 1, a[i][j], mark)) {
            if (dp[i][j][1] == 0) {
                dp[i][j][1] = 1 + dfs(i, j - 1, dp, mark); //left
            }
            max = Math.max(max, dp[i][j][1]);
        }
        if (canVisit(i - 1, j, a[i][j], mark)) {
            if (dp[i][j][2] == 0) {
                dp[i][j][2] = 1 + dfs(i - 1, j, dp, mark); //top
            }
            max = Math.max(max, dp[i][j][2]);
        }
        if (canVisit(i + 1, j, a[i][j], mark)) {
            if (dp[i][j][3] == 0) {
                dp[i][j][3] = 1 + dfs(i + 1, j, dp, mark); //bottom
            }
            max = Math.max(max, dp[i][j][3]);
        }
        mark[i][j] = false;
        return max;
    }
}
