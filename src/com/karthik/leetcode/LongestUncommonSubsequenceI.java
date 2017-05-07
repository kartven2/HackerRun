/*
 * Leetcode: https://leetcode.com/problems/longest-uncommon-subsequence-i/#/description
 * Given a group of two strings, you need to find the longest uncommon
 * subsequence of this group of two strings. The longest uncommon subsequence
 * is defined as the longest subsequence of one of these strings and this
 * subsequence should not be any subsequence of the other strings.
 * A subsequence is a sequence that can be derived from one sequence by
 * deleting some characters without changing the order of the remaining elements.
 * Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 * The input will be two strings, and the output needs to be the length of
 * the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestUncommonSubsequenceI {

    public int findLUSlength(String a, String b) {
        if (a != null && b != null && a.equals(b) || (a == null && b == null)) {
            return -1;
        }
        if (a == null || a.length() == 0) {
            return b.length();
        }
        if (b == null || b.length() == 0) {
            return a.length();
        }
        return Math.max(a.length(), b.length());
    }
}
