/*
 * Leetcode: https://leetcode.com/problems/sparse-matrix-multiplication/description/
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SparseMatrixMultiplication {

    public int[][] multiply(int[][] a, int[][] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return null;
        }
        int n = a.length, m = a[0].length, bn = b.length, bm = b[0].length;
        Map<Integer, Integer>[] map = new HashMap[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
            for (int j = 0; j < m; j++) {
                if (a[i][j] != 0) {
                    map[i].put(j, a[i][j]);
                }
            }
        }
        int[][] c = new int[n][bm];
        for (int i = 0; i < n; i++) {
            if (!map[i].isEmpty()) {
                for (Integer columnIndexA : map[i].keySet()) {
                    int vala = map[i].get(columnIndexA);
                    for (int j = 0; j < bm; j++) {
                        int valb = b[columnIndexA][j];
                        c[i][j] += (vala * valb);
                    }
                }
            }
        }
        return c;
    }
}
