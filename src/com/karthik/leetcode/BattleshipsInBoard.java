/*
 * LeetCode: https://leetcode.com/problems/battleships-in-a-board/description/
 * Given an 2D board, count how many battleships are in it.
 * The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically.
 * In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BattleshipsInBoard {

    public int countBattleships(char[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int ans = 0, n = a.length, m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 'X') {
                    if (i > 0 && j > 0 && (a[i - 1][j] == '.' && a[i][j - 1] == '.')) {
                        ans++;
                    } else if (i == 0 && j == 0) {
                        ans++;
                    } else if (i == 0 && (a[i][j - 1] == '.')) {
                        ans++;
                    } else if (j == 0 && (a[i - 1][j] == '.')) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
