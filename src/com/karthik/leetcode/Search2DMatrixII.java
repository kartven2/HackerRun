/*
 * Leetcode: https://leetcode.com/problems/search-a-2d-matrix-ii/#/description
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] a, int key) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }
        int n = a.length, m = a[0].length, i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if (a[i][j] == key) {
                return true;
            } else if (a[i][j] < key) {
                j++;
            } else if (a[i][j] > key) {
                i--;
            }
        }
        return false;
    }
}
