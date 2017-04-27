/*
 * Leetcode: https://leetcode.com/problems/n-queens-ii/#/description
 *
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations,
 * return the total number of distinct solutions.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NQueensII {

    private int count;

    public int totalNQueens2(int n) {
        if (n <= 0 || n > 1 && n < 4) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        dfs2(n, 0, new int[n]);
        return count;
    }

    private void dfs2(int n, int r, int[] col) {
        if (r == n) {
            count++;
        }
        boolean[] row = new boolean[n];
        for (int i = 0; i < r; i++) {
            row[col[i]] = true;
            if (col[i] + (r - i) < n) {
                row[col[i] + (r - i)] = true;
            }
            if (col[i] - (r - i) >= 0) {
                row[col[i] - (r - i)] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!row[i]) {
                col[r] = i;
                dfs2(n, r + 1, col);
                col[r] = 0;
            }
        }
    }

    public int totalNQueens(int n) {
        if (n <= 0 || n > 1 && n < 4) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        dfs(n, 0, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
        return count;
    }

    private void dfs(int n, int r, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (r == n) {
            count++;
            return;
        }
        for (int c = 0; c < n; c++) {
            int id1 = c - r + n, id2 = c + r;
            if (cols[c] || d1[id1] || d2[id2]) {
                continue;
            }
            cols[c] = true;
            d1[id1] = true;
            d2[id2] = true;
            dfs(n, r + 1, cols, d1, d2);
            cols[c] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
    }
}
