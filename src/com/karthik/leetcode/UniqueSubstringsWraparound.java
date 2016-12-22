/*
 * LeetCode: https://leetcode.com/problems/unique-substrings-in-wraparound-string/
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
 * so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p
 * are present in s. In particular, your input is the string p and you need to output the number of
 * different non-empty substrings of p in the string s.
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniqueSubstringsWraparound {

    private boolean isAdjChar(char a, char b) {
        int aidx = a - 'a';
        int bidx = b - 'a';
        if (aidx == bidx) {
            return false;
        }
        if (aidx == 25 && bidx == 0) {
            return true;
        }
        return (aidx + 1) == bidx;
    }

    public int findSubstringInWraproundString(String p) {
        if (p == null || p.trim().isEmpty()) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int n = p.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) >= 'a' && p.charAt(i) <= 'z') {
                dp[i][i] = true;
                set.add(p.charAt(i) + "");
            }
        }

        for (int k = 1; k < n; k++) {
            for (int i = 0, j = i + k; j < n;) {
                dp[i][j] = dp[i][j - 1] && dp[i + 1][j] && isAdjChar(p.charAt(j - 1), p.charAt(j));
                if (dp[i][j]) {
                    set.add(p.substring(i, j + 1));
                }
                i++;
                j++;
            }
        }
        return set.size();
    }

    public static void main(String... args) {
        UniqueSubstringsWraparound usw = new UniqueSubstringsWraparound();
        System.out.println(usw.findSubstringInWraproundString("cac"));
    }
}
