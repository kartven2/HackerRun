/*
 * LeetCode: https://leetcode.com/problems/longest-common-prefix/#/description
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int n = strs.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
            }
        }
        int d = 0;
        while (d < min) {
            char c = strs[0].charAt(d);
            for (int j = 1; j < n; j++) {
                if (strs[j].charAt(d) != c) {
                    return strs[j].substring(0, d);
                }
            }
            d++;
        }
        return strs[0].substring(0, min);
    }
}
