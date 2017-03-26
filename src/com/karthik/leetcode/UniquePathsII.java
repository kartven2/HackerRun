/*
 * Leetcode: https://leetcode.com/problems/unique-paths-ii/#/description
 *
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids.
 * How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length;
        if (a[n - 1][m - 1] == 1 || a[0][0] == 1) {
            return 0;
        }
        int[][] c = new int[n][m];
        for (int i = 0; i < n && a[i][0] == 0; i++) {
            c[i][0] = 1;
        }
        for (int j = 0; j < m && a[0][j] == 0; j++) {
            c[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i][j] == 0) {
                    c[i][j] = c[i - 1][j] + c[i][j - 1];
                }
            }
        }
        return c[n - 1][m - 1];
    }

    public int uniquePathsWithObstacles2(int[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length;
        if (a[n - 1][m - 1] == 1 || a[0][0] == 1) {
            return 0;
        }
        int[] c = new int[n];
        for (int i = 0; i < n && a[i][0] == 0; i++) {
            c[i] = 1;
        }
        boolean start = false;
        for (int j = 1; j < m; j++) {
            if (a[0][j] == 1 || start) {
                c[0] = 0;
                start = true;
            }
            for (int i = 1; i < n; i++) {
                if (a[i][j] == 0) {
                    c[i] += c[i - 1];
                } else {
                    c[i] = 0;
                }
            }
        }
        return c[n - 1];
    }

    public static void main(String... args) {
        int[][] a = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        UniquePathsII upq = new UniquePathsII();
        System.out.println(upq.uniquePathsWithObstacles2(a));
    }
}
