/*
 * leetCode: https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/#/description
 *
 * Given a string and a string dictionary, find the longest string in the dictionary that can be
 * formed by deleting some characters of the given string. If there are more than one possible results,
 * return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestWordDictionaryDeleting {

    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.trim().length() == 0) {
            return "";
        }
        String ans = "";
        for (String x : d) {
            int i = 0, j = 0;
            while (i < s.length() && j < x.length()) {
                if (s.charAt(i) == x.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == x.length()) {
                if (ans.length() <= x.length()) {
                    if (ans.length() < x.length() || x.compareTo(ans) < 0) {
                        ans = x;
                    }
                }
            }
        }
        return ans;
    }
}