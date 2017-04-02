/*
 * https://leetcode.com/problems/palindrome-partitioning/#/description
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 */
package com.karthik.leetcode;

import java.util.ArrayList;
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j + i < n; j++) {
                if (i == 0) {
                    dp[j][j] = true;
                    if (j + 1 < n) {
                        dp[j + 1][j] = true;
                    }
                } else if (s.charAt(j) == s.charAt(j + i) && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }

        List<Integer>[] list = (List<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (dp[i][j]) {
                    if (list[i] == null) {
                        list[i] = new ArrayList<>();
                    }
                    list[i].add(j);
                }
            }
        }

        collect(result, new ArrayList<>(), s, 0, list);

        return result;
    }

    private void collect(List<List<String>> result, List<String> split, String s, int i, List<Integer>[] list) {
        if (i >= s.length()) {
            List<String> nl = new ArrayList<>();
            for (String x : split) {
                nl.add(x);
            }
            result.add(nl);
            return;
        }
        for (int end : list[i]) {
            split.add(s.substring(i, ++end));
            collect(result, split, s, end, list);
            split.remove(split.size() - 1);
        }
    }
}
