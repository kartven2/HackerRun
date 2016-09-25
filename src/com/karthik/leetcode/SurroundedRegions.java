/*
 * LeetCode problem :
 * https://leetcode.com/problems/surrounded-regions/
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SurroundedRegions {

    class UF {

        private int[] sz;
        private int[] id;
        private int count;

        UF(int n) {
            count = n++;
            sz = new int[n];
            id = new int[n];
            for (int i = 0; i < n; i++) {
                sz[i] = 1;
                id[i] = i;
            }
        }

        private int count() {
            return count;
        }

        private boolean connected(int u, int v) {
            return find(u) == find(v);
        }

        private int find(int p) {
            int r = p, q;
            for (; r != id[r];) {
                r = id[r];
            }
            for (; p != r;) {
                q = id[p];
                id[p] = r;
                p = q;
            }
            return r;
        }

        private void union(int u, int v) {
            int rp = find(u), rq = find(v);
            if (rp == rq) {
                return;
            }
            if (sz[rp] < sz[rq]) {
                id[rp] = rq;
                sz[rq] += sz[rp];
            } else {
                id[rq] = rp;
                sz[rp] += sz[rq];
            }
            count--;
        }
    }

    private int coord(int m, int i, int j) {
        return m * i + j + 1;
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int n = board.length, m = board[0].length;
        UF uf = new UF(n * m);
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                uf.union(0, coord(m, i, 0));
            }
            if (board[i][m - 1] == 'O') {
                uf.union(0, coord(m, i, m - 1));
            }
        }
        for (int j = 1; j < m - 1; j++) {
            if (board[0][j] == 'O') {
                uf.union(0, coord(m, 0, j));
            }
            if (board[n - 1][j] == 'O') {
                uf.union(0, coord(m, n - 1, j));
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O') {
                    int c = coord(m, i, j);
                    if (board[i - 1][j] == 'O') {
                        uf.union(c, c - m);
                    }
                    if (board[i + 1][j] == 'O') {
                        uf.union(c, c + m);
                    }
                    if (board[i][j - 1] == 'O') {
                        uf.union(c, c - 1);
                    }
                    if (board[i][j + 1] == 'O') {
                        uf.union(c, c + 1);
                    }
                }
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O' && !uf.connected(0, coord(m, i, j))) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String... args) {
        SurroundedRegions sr = new SurroundedRegions();
        char[][] input = {"XXXXOOXXO".toCharArray(), "OOOOXXOOX".toCharArray(), "XOXOOXXOX".toCharArray(), "OOXXXOOOO".toCharArray(), "XOOXXXXXO".toCharArray(),
            "OOXOXOXOX".toCharArray(), "OOOXXOXOX".toCharArray(), "OOOXOOOXO".toCharArray(), "OXOOOXOXO".toCharArray()};
        sr.solve(input);
    }
}
