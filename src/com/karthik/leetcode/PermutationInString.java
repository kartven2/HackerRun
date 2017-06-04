/*
 * LeetCode problem: https://leetcode.com/problems/permutation-in-string/#/description
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's permutations
 * is the substring of the second string.
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PermutationInString {

    public static void main(String... args) {
        PermutationInString ps = new PermutationInString();
        boolean res = ps.checkInclusion("ab", "ooollebaoooleh");
        System.out.println(res);

    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) {
            return true;
        }
        if (s2 == null || s2.isEmpty() || s2.length() < s1.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
            int[] c = Arrays.copyOf(count, count.length);
            if (dfs(s1, s2, c, i, 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(String s1, String s2, int[] count, int i, int len) {
        if (len == s1.length()) {
            return true;
        }
        if (count[s2.charAt(i) - 'a'] <= 0) {
            return false;
        }
        count[s2.charAt(i) - 'a']--;
        return dfs(s1, s2, count, i + 1, len + 1);
    }
}
