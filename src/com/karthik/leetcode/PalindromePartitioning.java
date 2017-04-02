/*
 * https://leetcode.com/problems/palindrome-partitioning/#/description
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.trim().isEmpty()) {
            return result;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0, k = 0; i < n; i++, k++) {
            for (int j = i; j + k < n; j++) {
                if (k == 0) {
                    dp[j][j + k] = true;
                } else if (s.charAt(j) == s.charAt(j + k) && dp[j + 1][j + k - 1]) {
                    dp[j][j + k] = true;
                }
            }
        }
        return result;
    }
}
