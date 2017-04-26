/*
 * Leetcode: https://leetcode.com/problems/search-a-2d-matrix/#/description
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Search2DMatrix {

    public static void main(String... args) {
        int[][] a = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        Search2DMatrix sdm = new Search2DMatrix();
        sdm.searchMatrix(a, 3);
    }

    public boolean searchMatrix(int[][] a, int key) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }
        int n = a.length, m = a[0].length, lo = 0, hi = n - 1, row = 0, mid = 0;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (a[mid][0] == key) {
                return true;
            } else if (a[mid][0] < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        row = lo;
        if (row == 0) {
            return false;
        }
        row--;
        lo = 0;
        hi = m - 1;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (a[row][mid] == key) {
                return true;
            } else if (a[row][mid] < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
