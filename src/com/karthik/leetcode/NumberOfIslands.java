/*
 * LeetCode: https://leetcode.com/problems/number-of-islands/#/description
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NumberOfIslands {

    public static void main(String... args) {
        char[][] a = {"111".toCharArray(), "101".toCharArray(), "111".toCharArray()};
        NumberOfIslands ni = new NumberOfIslands();
        ni.numIslands(a);
    }

    private void replaceChars(int[][] a, int i, int j, int c) {
        if (i == a.length || j == a[0].length || i < 0 || j < 0) {
            return;
        }
        if (a[i][j] == 1) {
            a[i][j] = c;
            replaceChars(a, i + 1, j, c);
            replaceChars(a, i - 1, j, c);
            replaceChars(a, i, j - 1, c);
            replaceChars(a, i, j + 1, c);
        }
    }

    public int numIslands(char[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length, c = 2;
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i][j] = a[i][j] - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (b[i][j] == 1) {
                    replaceChars(b, i, j, c++);
                }
            }
        }
        return c - 2;
    }
}
