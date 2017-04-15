/*
 * LeetCode: https://leetcode.com/problems/minimum-window-substring/#/description
 * Given a string S and a string T, find the minimum window in S which will contain
 * all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".

 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinWindowSubstring {

    public static void main(String... args) {
        MinWindowSubstring mws = new MinWindowSubstring();
        mws.minWindow("a", "a");
    }

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        int[] map = new int[128];
        int sn = s.length(), tn = t.length();
        for (int i = 0; i < tn; i++) {
            char c = t.charAt(i);
            map[c]++;
        }
        int l = 0, r = 0, cnt = tn, d = Integer.MAX_VALUE, minl = 0;
        while (r < sn) {
            if (map[s.charAt(r++)]-- > 0) {
                cnt--;
            }
            while (cnt == 0) {
                if (r - 1 - l < d) {
                    d = r - 1 - l;
                    minl = l;
                }
                if (map[s.charAt(l++)]++ == 0) {
                    cnt++;
                }
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(minl, minl + d + 1);
    }
}
