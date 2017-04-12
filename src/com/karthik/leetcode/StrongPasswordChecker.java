/*
 * Leetcode: https://leetcode.com/problems/strong-password-checker/#/description
 *
 * A password is considered strong if below conditions are all met:
 *
 * It has at least 6 characters and at most 20 characters.
 * It must contain at least one lowercase letter, at least one uppercase letter,
 * and at least one digit.
 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 * Write a function strongPasswordChecker(s),
 * that takes a string s as input, and return the MINIMUM change required to make s a strong password.
 * If s is already strong, return 0.
 * Insertion, deletion or replace of any one character are all considered as one change.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class StrongPasswordChecker {

    public int strongPasswordChecker(String s) {
        if (s == null || s.length() == 0) {
            return 6;
        }
        int n = s.length(), st = 1;
        boolean lc = false, dc = false, uc = false;
        int[] dp = new int[n + 1];
        int[] need = new int[n + 1];
        boolean[] tp = new boolean[n + 1];
        dp[0] = 6;
        need[0] = 3;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int v = getCharVal(c);
            need[i + 1] = need[i];
            switch (v) {
                case 0:
                    if (!lc) {
                        lc = true;
                        need[i + 1] = need[i] - 1;
                    }
                    break;
                case 1:
                    if (!uc) {
                        uc = true;
                        need[i + 1] = need[i] - 1;
                    }
                    break;
                case 2:
                    if (!dc) {
                        dc = true;
                        need[i + 1] = need[i] - 1;
                    }
                    break;
                default:
                    break;
            }
            if (v > -1) {
                if (c == s.charAt(i - 1) && st == 2) {
                    tp[i + 1] = true;
                    st = 0;
                } else if (c == s.charAt(i - 1)) {
                    st++;
                } else {
                    st = 1;
                }
            } else {
                st = 0;
            }
            if (i < 6) {
                dp[i + 1] = Math.max(dp[i] - 1, need[i + 1]);
            } else if (i >= 6 && i <= 20) {
            }
        }
        return dp[n];
    }

    private int getCharVal(char c) {
        if (c >= 'a' && c <= 'z') {
            return 0;
        }
        if (c >= 'A' && c <= 'Z') {
            return 1;
        }
        if (c >= '0' && c <= '9') {
            return 2;
        }
        return -1;
    }
}
