/*
 * Leetcode: https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Remove the minimum number of invalid parentheses in order
 * to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the
 * parentheses ( and ). *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastRemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        if (s.trim().length() == 0) {
            result.add(s);
            return result;
        }
        collect(result, 0, 0, new char[]{'(', ')'}, s);
        return result;
    }

    private void collect(List<String> r, int i, int j,
            char[] p, String s) {
        for (int count = 0, k = i; k < s.length(); k++) {
            if (s.charAt(k) == p[0]) {
                count++;
            }
            if (s.charAt(k) == p[1]) {
                count--;
            }
            if (count >= 0) {
                continue;
            }
            for (int l = j; l <= k; l++) {
                if (s.charAt(l) == p[1] && (l == j || s.charAt(l - 1) != p[1])) {
                    collect(r, k, l, p, s.substring(0, l) + s.substring(l + 1));
                }
            }
            return;
        }
        String rev = new StringBuilder(s).reverse().toString();
        if (p[0] == '(') {
            collect(r, 0, 0, new char[]{')', '('}, rev);
        } else {
            r.add(rev);
        }
    }
}
