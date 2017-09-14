/*
 * Leetcode: https://leetcode.com/problems/one-edit-distance/description/
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if ((s == null && t == null) || (s.isEmpty() && t.isEmpty()) || Math.abs(s.length() - t.length()) > 1 || s.equals(t)) {
            return false;
        }
        if (s.length() == t.length()) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    cnt++;
                }
                if (cnt == 2) {
                    return false;
                }
            }
            return true;
        }
        if (t.length() > s.length()) {
            String tmp = t;
            t = s;
            s = tmp;
        }
        int i = 0, j = 0, count = 0;
        while (i < s.length()) {
            if (j == t.length()) {
                return (i == s.length() - 1);
            }
            while (i < s.length() && s.charAt(i) != t.charAt(j)) {
                count++;
                if (count == 2) {
                    return false;
                }
                i++;
            }
            i++;
            j++;
        }
        return true;
    }
}
