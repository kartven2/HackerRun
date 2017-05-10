/*
 * Leetcode: https://leetcode.com/problems/pacific-atlantic-water-flow/#/description
 *
 * Given an m x n matrix of non-negative integers representing the height
 * of each unit cell in a continent, the "Pacific ocean" touches the left
 * and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another
 * one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * 
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PacificAtlanticWaterFlow {

    public List<int[]> pacificAtlantic(int[][] a) {
        List<int[]> res = new LinkedList<>();
        if (a == null || a.length == 0 || a[0].length == 0) {
            return res;
        }
        int n = a.length, m = a[0].length;
        boolean[][] bp = new boolean[n][m];
        boolean[][] ba = new boolean[n][m];
        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            bp[i][0] = true;
            pq.add(new int[]{i, 0});
            ba[i][m - 1] = true;
            aq.add(new int[]{i, m - 1});
        }
        for (int j = 0; j < m; j++) {
            bp[0][j] = true;
            pq.add(new int[]{0, j});
            ba[n - 1][j] = true;
            aq.add(new int[]{n - 1, j});
        }
        bfs(pq, bp, a);
        bfs(aq, ba, a);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (bp[i][j] && ba[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }

    private void bfs(Queue<int[]> q, boolean[][] marked, int[][] a) {
        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int n = a.length, m = a[0].length;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            for (int[] adjacent : dir) {
                int adjx = current[0] + adjacent[0];
                int adjy = current[1] + adjacent[1];
                if (adjx < 0 || adjy < 0 || adjx >= n || adjy >= m || marked[adjx][adjy] || a[current[0]][current[1]] > a[adjx][adjy]) {
                    continue;
                }
                marked[adjx][adjy] = true;
                q.add(new int[]{adjx, adjy});
            }
        }
    }

    public static void main(String... args) {
        PacificAtlanticWaterFlow pwf = new PacificAtlanticWaterFlow();
        int[][] a = {{1, 2, 2, 3, 5},
        {3, 2, 3, 4, 4},
        {2, 4, 5, 3, 1},
        {6, 7, 1, 4, 5},
        {5, 1, 1, 2, 4}};
        pwf.pacificAtlantic(a);
    }

    static class Pair {

        private boolean p;
        private boolean a;

        Pair(boolean p, boolean a) {
            this.p = p;
            this.a = a;
        }
    }

    private Pair dfs(Pair[][] dp, int[][] a, int i, int j, int n, int m, int prev, boolean[][] marked) {
        if (i < 0 || j < 0) {
            return new Pair(true, false);
        }

        if (i >= n || j >= m) {
            return new Pair(false, true);
        }

        if (prev < a[i][j]) {
            return new Pair(false, false);
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (marked[i][j]) {
            return new Pair(false, false);
        }

        marked[i][j] = true;
        Pair l, r, t, b;
        boolean pa, at;
        l = dfs(dp, a, i - 1, j, n, m, a[i][j], marked);
        pa = l.p;
        at = l.a;
        if (!(pa && at)) {
            r = dfs(dp, a, i + 1, j, n, m, a[i][j], marked);
            pa |= r.p;
            at |= r.a;
            if (!(pa && at)) {
                t = dfs(dp, a, i, j - 1, n, m, a[i][j], marked);
                pa |= t.p;
                at |= t.a;
                if (!(pa && at)) {
                    b = dfs(dp, a, i, j + 1, n, m, a[i][j], marked);
                    pa |= b.p;
                    at |= b.a;
                }
            }
        }
        marked[i][j] = false;
        return new Pair(pa, at);
    }

    public List<int[]> pacificAtlantic2(int[][] a) {
        List<int[]> result = new ArrayList<>();
        if (a == null || a.length == 0 || a[0].length == 0) {
            return result;
        }
        int n = a.length, m = a[0].length;
        Pair[][] dp = new Pair[n][m];
        dp[0][m - 1] = new Pair(true, true);
        dp[n - 1][0] = new Pair(true, true);
        boolean[][] marked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = dfs(dp, a, i, j, n, m, Integer.MAX_VALUE, marked);
                if (dp[i][j].p && dp[i][j].a) {
                    int[] sub = new int[2];
                    sub[0] = i;
                    sub[1] = j;
                    result.add(sub);
                }
            }
        }
        return result;
    }
}
