/*
 * Leetcode: https://leetcode.com/problems/wildcard-matching/
 *
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WildcardMatching {

    public boolean isMatch(String sa, String pa) {
        char[] s = sa.toCharArray();
        char[] p = pa.toCharArray();
        int n = s.length;
        int m = p.length;
        int i = 0, j = 0, stidx = -1, match = 0;
        while (i < n) {
            if (j < m && (p[j] == s[i] || p[j] == '?')) {
                i++;
                j++;
            } else if (j < m && p[j] == '*') {
                stidx = j;
                match = i;
                j++;
            } else if (stidx != -1) {
                i = ++match;
                j = stidx + 1;
            } else {
                return false;
            }
        }
        while (j < m && p[j] == '*') {
            j++;
        }
        return j == m;
    }

    public static void main(String... args) {
        WildcardMatching wm = new WildcardMatching();
        System.out.println(wm.isMatch("aa", "a*"));
    }
}
