/*
 * LeetCode Problem: Word Search
 * https://leetcode.com/problems/word-search/
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordSearch {

    public static void main(String... args) {
        char[][] sample = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(sample, "ABCESEEEFS"));
    }

    private boolean exists(char[][] board, char[] wds, int i, int j, int n, int m, int d, int wl) {
        if (d == wl) {
            return true;
        }
        if (i < 0 || j < 0 || i >= n || j >= m || board[i][j] != wds[d]) {
            return false;
        }
        board[i][j] ^= 256;
        if (exists(board, wds, i - 1, j, n, m, d + 1, wl) || exists(board, wds, i, j - 1, n, m, d + 1, wl)
                || exists(board, wds, i + 1, j, n, m, d + 1, wl) || exists(board, wds, i, j + 1, n, m, d + 1, wl)) {
            return true;
        }
        board[i][j] ^= 256;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0) {
            return false;
        }
        int n = board.length, m = board[0].length;
        char[] wds = word.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (exists(board, wds, i, j, n, m, 0, word.length())) {
                    return true;
                }
            }
        }
        return false;
    }
}
