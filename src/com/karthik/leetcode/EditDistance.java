/*
 * LeetCode: https://leetcode.com/problems/edit-distance/#/description
 * Given two words word1 and word2, find the minimum number of steps
 * required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EditDistance {

    private int min(int a, int b, int c) {
        return a < b && a < c ? a : b < c ? b : c;
    }

    public int minDistance(String w1, String w2) {
        if (w1 == null && w2 == null) {
            return 0;
        }
        if (w1 == null || w1.length() == 0) {
            return w2.length();
        }
        if (w2 == null || w2.length() == 0) {
            return w1.length();
        }
        int w1n = w1.length() + 1, w2n = w2.length() + 1;
        int[][] dp = new int[w1n][w2n];
        for (int j = 0; j < w2n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < w1n; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < w1n; i++) {
            for (int j = 1; j < w2n; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[w1n - 1][w2n - 1];
    }
}
