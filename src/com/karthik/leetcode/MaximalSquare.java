/*
 * Leetcode: https://leetcode.com/problems/maximal-square/
 *
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 4.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximalSquare {

    private static final int MIN = Integer.MIN_VALUE;

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] aux = new int[n][m];
        boolean fr1 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == '1') {
                aux[0][i] = 1;
                fr1 = true;
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == '1') {
                aux[i][0] = 1;
                fr1 = true;
            }
        }
        if (fr1 && (n == 1 || m == 1)) {
            return 1;
        }
        int max = MIN;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    aux[i][j] = Math.min(aux[i][j - 1], Math.min(aux[i - 1][j], aux[i - 1][j - 1])) + 1;
                    if (aux[i][j] > max) {
                        max = aux[i][j];
                    }
                }
            }
        }
        return max == MIN ? fr1 ? 1 : 0 : max * max;
    }
}
