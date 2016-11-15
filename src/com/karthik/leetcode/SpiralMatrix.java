/*
 * Leetcode: https://leetcode.com/problems/spiral-matrix/
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SpiralMatrix {

    private void addForX(int[][] matrix, int lo, int hi, int x, List<Integer> result) {
        for (int y = lo; y <= hi; y++) {
            result.add(matrix[x][y]);
        }
    }

    private void addForY(int[][] matrix, int lo, int hi, int y, List<Integer> result) {
        for (int x = lo; x <= hi; x++) {
            result.add(matrix[x][y]);
        }
    }

    private void revAddForX(int[][] matrix, int lo, int hi, int x, List<Integer> result) {
        for (int y = hi; y >= lo; y--) {
            result.add(matrix[x][y]);
        }
    }

    private void revAddForY(int[][] matrix, int lo, int hi, int y, List<Integer> result) {
        for (int x = hi; x >= lo; x--) {
            result.add(matrix[x][y]);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix.length == 0) {
            return result;
        }
        //getSpiralOrder(matrix, result, 0, 0, matrix.length - 1, matrix[0].length - 1);
        int i = 0, j = 0, n = matrix.length - 1, m = matrix[0].length - 1;
        while (i <= n && j <= m) {
            if (i == n && j == m) {
                result.add(matrix[i][j]);
                return result;
            }
            if (i == n) {
                addForX(matrix, j, m, i, result);
                return result;
            }
            if (j == m) {
                addForY(matrix, i, n, j, result);
                return result;
            }
            addForX(matrix, j, m - 1, i, result);
            addForY(matrix, i, n - 1, m, result);
            revAddForX(matrix, j + 1, m, n, result);
            revAddForY(matrix, i + 1, n, j, result);
            i++;
            j++;
            n--;
            m--;
        }
        return result;
    }

    private void getSpiralOrder(int[][] matrix, List<Integer> result, int i, int j, int n, int m) {
        if (i > n || j > m) {
            return;
        }
        if (i == n && j == m) {
            result.add(matrix[i][j]);
            return;
        }
        if (i == n) {
            addForX(matrix, j, m, i, result);
            return;
        }
        if (j == m) {
            addForY(matrix, i, n, j, result);
            return;
        }
        addForX(matrix, j, m - 1, i, result);
        addForY(matrix, i, n - 1, m, result);
        revAddForX(matrix, j + 1, m, n, result);
        revAddForY(matrix, i + 1, n, j, result);
        n--;
        m--;
        i++;
        j++;
        getSpiralOrder(matrix, result, i, j, n, m);
    }
}
