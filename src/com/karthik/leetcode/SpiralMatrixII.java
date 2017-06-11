/*
 * LeetCode Problem: https://leetcode.com/problems/spiral-matrix-ii/#/description
 *
 * Given an integer n, generate a square matrix filled with elements from
 * 1 to n2 in spiral order
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SpiralMatrixII {

    public static void main(String... args) {
        SpiralMatrixII sp2 = new SpiralMatrixII();
        sp2.generateMatrix(3);
    }

    private int fillLayer(int[][] a, int lo, int hi, int v) {
        if (lo == hi) {
            a[lo][hi] = v++;
            return v;
        }
        for (int j = lo; j < hi; j++) {
            a[lo][j] = v++;
        }
        for (int i = lo; i < hi; i++) {
            a[i][hi] = v++;
        }
        for (int j = hi; j > lo; j--) {
            a[hi][j] = v++;
        }
        for (int i = hi; i > lo; i--) {
            a[i][lo] = v++;
        }
        return v;
    }

    public int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        if (n == 0) {
            return a;
        }
        int lo = 0, hi = n - 1, v = 1, max = n * n;
        while (lo <= hi && v <= max) {
            v = fillLayer(a, lo, hi, v);
            lo++;
            hi--;
        }
        return a;
    }
}
