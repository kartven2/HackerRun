/*
 * LeetCode: https://leetcode.com/problems/game-of-life/#/description
 * According to the Wikipedia's article: "The Game of Life, also known
 * simply as Life, is a cellular automaton devised by the British mathematician
 * John Horton Conway in 1970."
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up: 
 * Could you solve it in-place? Remember that the board needs to be updated at the same time:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite, which would cause problems when the active
 * area encroaches the border of the array. How would you address these problems?
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GameOfLife {

    public void gameOfLife(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return;
        }
        int n = a.length, m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = 0;
                if (i - 1 >= 0 && j - 1 >= 0 && a[i - 1][j - 1] > 0) {
                    count++;
                }
                if (i - 1 >= 0 && a[i - 1][j] > 0) {
                    count++;
                }
                if (i - 1 >= 0 && j + 1 < m && a[i - 1][j + 1] > 0) {
                    count++;
                }
                if (j - 1 >= 0 && a[i][j - 1] > 0) {
                    count++;
                }
                if (j + 1 < m && a[i][j + 1] > 0) {
                    count++;
                }
                if (i + 1 < n && j - 1 >= 0 && a[i + 1][j - 1] > 0) {
                    count++;
                }
                if (i + 1 < n && a[i + 1][j] > 0) {
                    count++;
                }
                if (i + 1 < n && j + 1 < m && a[i + 1][j + 1] > 0) {
                    count++;
                }
                if (a[i][j] < 1 && count == 3) {
                    a[i][j] = -1;
                }
                if (a[i][j] > 0 && (count < 2 || count > 3)) {
                    a[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 2) {
                    a[i][j] = 0;
                }
                if (a[i][j] == -1) {
                    a[i][j] = 1;
                }
            }
        }
    }
}
