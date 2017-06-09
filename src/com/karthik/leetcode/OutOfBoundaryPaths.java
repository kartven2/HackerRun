/*
 * LeetCode: https://leetcode.com/problems/out-of-boundary-paths/#/description
 *
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
 * you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
 * However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 109 + 7.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class OutOfBoundaryPaths {

    public static void main(String... args) {
        OutOfBoundaryPaths ob = new OutOfBoundaryPaths();
        System.out.println(ob.findPaths(2, 2, 2, 0, 0));
    }

    private static final long CONST = ((long) (1e9) + 7);

    public int findPaths(int m, int n, int N, int i, int j) {
        if (m <= 0 && n <= 0) {
            return 0;
        }
        int ans = 0;
        Long[][][] dp = new Long[m][n][N + 1];
        for (int k = 1; k <= N; k++) {
            long sub = walk(m, n, k, i, j, dp);
            ans = (int) ((ans + sub) % CONST);
        }
        return ans;
    }

    private long walk(int m, int n, int k, int i, int j, Long[][][] dp) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return k == 0 ? 1 : 0;
        }
        if (k == 0) {
            return 0;
        }
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        dp[i][j][k] = (walk(m, n, k - 1, i - 1, j, dp)
                + walk(m, n, k - 1, i + 1, j, dp)
                + walk(m, n, k - 1, i, j - 1, dp)
                + walk(m, n, k - 1, i, j + 1, dp)) % CONST;
        return dp[i][j][k];
    }
}
