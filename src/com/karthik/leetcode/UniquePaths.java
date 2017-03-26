/*
 * Leetcode: https://leetcode.com/problems/unique-paths/#/description
 *
 * A robot is located at the top-left corner of a m x n grid
 * (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            a[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            a[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }
        return a[m - 1][n - 1];
    }

    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[] a = new int[m];
        Arrays.fill(a, 1);
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                a[i] += a[i - 1];
            }
        }
        return a[m - 1];
    }
}
