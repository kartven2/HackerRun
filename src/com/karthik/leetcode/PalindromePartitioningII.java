/*
 * LeetCode: https://leetcode.com/problems/palindrome-partitioning-ii/
 * Given a string s, partition s such that every substring of the partition
 * is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced
 * using 1 cut.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PalindromePartitioningII {

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
            if (i > 0) {
                palindrome[i][i - 1] = true;
            }
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0, j = i + k; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j) && palindrome[i + 1][j - 1]) {
                    palindrome[i][j] = true;
                }
            }
        }
        int[] minCuts = new int[n];
        Arrays.fill(minCuts, n);
        minCuts[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (palindrome[j][i]) {
                    if (j == 0) {
                        minCuts[i] = 0;
                        break;
                    } else {
                        minCuts[i] = Math.min(minCuts[i], minCuts[j - 1] + 1);
                    }
                }
            }
        }
        return minCuts[n - 1];
    }

    public static void main(String... args) {
        PalindromePartitioningII pp = new PalindromePartitioningII();
        System.out.println(pp.minCut("abbab"));
    }
}
