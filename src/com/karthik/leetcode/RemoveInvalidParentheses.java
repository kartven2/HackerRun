/*
 * Leetcode: https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Remove the minimum number of invalid parentheses in order to make
 * the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.trim().length() == 0) {
            return result;
        }
        int n = s.length(), lpr = 0, rpr = 0;
        int[] lp = new int[n];
        int[] rp = new int[n];
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case '(':
                    lp[lpr++] = i;
                    break;
                case ')':
                    rp[rpr++] = i;
                    break;
                default:
                    break;
            }
        }
        int j = lpr, k = rpr, elp = 0, erp=0;
        for (; j > 0 && k > 0;) {
            if (lp[j] < rp[k]) {
                j--;
                k--;
            } else {
                j--;
                elp++;
            }
        }
    }
}
