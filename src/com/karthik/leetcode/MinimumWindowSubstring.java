/*
 * Leetcode: https://leetcode.com/problems/minimum-window-substring/#/description
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumWindowSubstring {

    public static void main(String... args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        mws.minWindow("bbaa", "aba");
    }

    public String minWindow(String s, String t) {
        if (t == null || s == null || t.length() == 0 || s.length() == 0 || s.length() < t.length()) {
            return "";
        }
        int tn = t.length(), sn = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tn; i++) {
            char c = t.charAt(i);
            Integer val = map.get(c);
            if (val == null) {
                val = 0;
            }
            map.put(c, ++val);
        }
        Map<Character, Integer> dup = new HashMap<>(map);
        int st = 0, lmt = tn;
        for (; st < sn; st++) {
            char c = s.charAt(st);
            Integer val = dup.get(c);
            if (val != null && val > 0) {
                lmt--;
                dup.put(c, --val);
                if (lmt == 0) {
                    break;
                }
            }
        }
        if (lmt > 0) {
            return "";
        }
        int[][] a = new int[128][sn + 1];
        int ms = 0, me = st;
        if (me - ms + 1 == tn) {
            return s.substring(ms, me + 1);
        }
        for (int i = 0; i < sn; i++) {
            char c = s.charAt(i);
            if (map.get(c) != null) {
                a[c][++a[c][0]] = i;
                if (i >= st) {
                    int min = i;
                    for (char n : map.keySet()) {
                        int j = n;
                        if (a[j][0] >= map.get(n)) {
                            min = Math.min(min, a[j][a[j][0] - map.get(n) + 1]);
                        }
                    }
                    if (i - min < me - ms) {
                        ms = min;
                        me = i;
                    }
                }
            }
        }
        return s.substring(ms, me + 1);
    }
}
