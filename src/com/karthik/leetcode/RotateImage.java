/*
 * LeetCode: https://leetcode.com/problems/rotate-image/
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1 || matrix.length != matrix[0].length) {
            return;
        }
        for (int i = 0; i < matrix.length / 2; i++) {
            int min = i, max = matrix.length - 1 - i;
            for (int j = min, k = max; j < max && k > min; j++, k--) {
                int tmp = matrix[min][j];
                matrix[min][j] = matrix[k][min];
                matrix[k][min] = matrix[max][k];
                matrix[max][k] = matrix[j][max];
                matrix[j][max] = tmp;
            }
        }
    }
}
