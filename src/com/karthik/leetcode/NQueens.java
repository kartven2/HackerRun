/*
 * Leetcode: https://leetcode.com/problems/n-queens/#/description
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NQueens {

    public static void main(String... args) {
        NQueens nq = new NQueens();
        List<List<String>> result = nq.solveNQueens(5);
        for (List<String> solution : result) {
            for (String word : solution) {
                System.out.println(word);
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0 || n > 1 && n < 4) {
            return result;
        }
        if (n == 1) {
            List<String> sub = new ArrayList<>();
            sub.add("Q");
            result.add(sub);
            return result;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        int[] skip = new int[n];
        dfs(board, 0, n, result, skip);
        return result;
    }

    private void dfs(char[][] board, int r, int n, List<List<String>> result, int[] skip) {
        if (r == n) {
            List<String> sub = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                sub.add(new String(board[i]));
            }
            result.add(sub);
            return;
        }
        Set<Integer> skpset = new HashSet<>();
        for (int i = 0; i < r; i++) {
            skpset.add(skip[i]);
            if (skip[i] + (r - i) < n) {
                skpset.add(skip[i] + (r - i));
            }
            if (skip[i] - (r - i) >= 0) {
                skpset.add(skip[i] - (r - i));
            }
        }
        for (int c = 0; c < n; c++) {
            if (skpset.contains(c)) {
                continue;
            }
            board[r][c] = 'Q';
            skip[r] = c;
            dfs(board, r + 1, n, result, skip);
            board[r][c] = '.';
            skip[r] = 0;
        }
    }
}
