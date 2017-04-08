/*
 * LeetCode: https://leetcode.com/problems/valid-sudoku/#/description
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] a) {
        if (a == null || a.length != 9 || a[0].length != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            boolean[] rc = new boolean[9];
            boolean[] cc = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (a[i][j] != '.') {
                    if (rc[a[i][j] - '1']) {
                        return false;
                    }
                    rc[a[i][j] - '1'] = true;
                }
                if (a[j][i] != '.') {
                    if (cc[a[j][i] - '1']) {
                        return false;
                    }
                    cc[a[j][i] - '1'] = true;
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                boolean[] cube = new boolean[9];
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (a[k][l] != '.') {
                            if (cube[a[k][l] - '1']) {
                                return false;
                            }
                            cube[a[k][l] - '1'] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
