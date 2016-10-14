/*
 * LeetCode: https://leetcode.com/problems/set-matrix-zeroes/
 * Given a m x n matrix, if an element is 0,
 * set its entire row and column to 0. Do it in place.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SetMatrixZeroes {

    public static void main(String... args) {
        int[][] mat = {{0, 0, 0, 5},
        {4, 3, 1, 4},
        {0, 1, 1, 4},
        {1, 2, 1, 3},
        {0, 0, 1, 1}};
        SetMatrixZeroes smz = new SetMatrixZeroes();
        smz.setZeroes(mat);
    }

    private int[][] a;
    private int n, m;
    private boolean fr, fc;

    private void setRowsZero(int colId) {
        for (int i = 0; i < n; i++) {
            a[i][colId] = 0;
        }
    }

    private void setColsZero(int rowId) {
        for (int i = 0; i < m; i++) {
            a[rowId][i] = 0;
        }
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        a = matrix;
        n = matrix.length;
        m = matrix[0].length;
        for (int i = 0; i < n && !fc; i++) {
            if (a[i][0] == 0) {
                fc = true;
            }
        }
        for (int i = 0; i < m && !fr; i++) {
            if (a[0][i] == 0) {
                fr = true;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i][j] == 0) {
                    a[0][j] = a[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (a[0][i] == 0) {
                setRowsZero(i);
            }
        }
        for (int i = 1; i < n; i++) {
            if (a[i][0] == 0) {
                setColsZero(i);
            }
        }
        if (fr) {
            setColsZero(0);
        }
        if (fc) {
            setRowsZero(0);
        }
    }
}
