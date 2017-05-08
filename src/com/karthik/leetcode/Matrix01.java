/*
 * Leetcode: https://leetcode.com/problems/01-matrix/#/description
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 *
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Matrix01 {

    static class Point {

        int i;
        int j;
        boolean marked = false;
        int value = Integer.MAX_VALUE;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int[][] updateMatrix(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return a;
        }
        int n = a.length, m = a[0].length;
        Point[][] p = new Point[n][m];
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = new Point(i, j);
                if (a[i][j] == 0) {
                    p[i][j].value = 0;
                    p[i][j].marked = true;
                    q.add(p[i][j]);
                }
            }
        }
        int[][] res = new int[n][m];
        while (!q.isEmpty()) {
            Point v = q.remove();
            v.marked = true;
            if (v.i - 1 >= 0 && !p[v.i - 1][v.j].marked && p[v.i - 1][v.j].value > v.value + 1) {
                p[v.i - 1][v.j].value = v.value + 1;
                q.add(p[v.i - 1][v.j]);
                res[v.i - 1][v.j] = p[v.i - 1][v.j].value;
            }
            if (v.i + 1 < n && !p[v.i + 1][v.j].marked && p[v.i + 1][v.j].value > v.value + 1) {
                p[v.i + 1][v.j].value = v.value + 1;
                q.add(p[v.i + 1][v.j]);
                res[v.i + 1][v.j] = p[v.i + 1][v.j].value;
            }
            if (v.j - 1 >= 0 && !p[v.i][v.j - 1].marked && p[v.i][v.j - 1].value > v.value + 1) {
                p[v.i][v.j - 1].value = v.value + 1;
                q.add(p[v.i][v.j - 1]);
                res[v.i][v.j - 1] = p[v.i][v.j - 1].value;
            }
            if (v.j + 1 < m && !p[v.i][v.j + 1].marked && p[v.i][v.j + 1].value > v.value + 1) {
                p[v.i][v.j + 1].value = v.value + 1;
                q.add(p[v.i][v.j + 1]);
                res[v.i][v.j + 1] = p[v.i][v.j + 1].value;
            }
        }
        return res;
    }
}
