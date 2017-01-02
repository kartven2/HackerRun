/*
 * LeetCode: https://leetcode.com/problems/longest-palindromic-substring/
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length(), startpos = 0, maxlen = Integer.MIN_VALUE;
        char[] a = s.toCharArray();
        for (int i = 1; i < n; i++) {
            int l = i - 1, r = i + 1, cl = 1;
            while (l >= 0 && r < n && a[l] == a[r]) {
                l--;
                r++;
                cl += 2;
            }
            if (cl > maxlen) {
                maxlen = cl;
                startpos = l + 1;
            }
            l = i - 1;
            r = i;
            cl = 0;
            while (l >= 0 && r < n && a[l] == a[r]) {
                l--;
                r++;
                cl += 2;
            }
            if (cl > maxlen) {
                maxlen = cl;
                startpos = l + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = startpos; i < startpos + maxlen; i++) {
            sb.append(a[i]);
        }
        return sb.toString();
    }
}
