/*
 * LeetCode: https://leetcode.com/problems/palindromic-substrings/tabs/description
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings
 * even they consist of same characters.
 * 
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.trim().length();
        if (n < 2) {
            return n;
        }
        boolean[][] a = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            a[i][i] = true;
            cnt++;
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0, j = i + len; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j) && (j == i + 1 || a[i + 1][j - 1])) {
                    cnt++;
                    a[i][j] = true;
                }
            }
        }
        return cnt;
    }
}
