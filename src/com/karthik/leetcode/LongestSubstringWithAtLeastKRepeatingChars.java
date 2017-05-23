/*
 * Leetcode:https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/#/description
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestSubstringWithAtLeastKRepeatingChars {

    public int longestSubstring(String s, int k) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(s.charAt(i));
        }
        int i, j, unique, atleastk, max = 0, idx;
        for (int a = 1; a <= set.size(); a++) {
            i = 0;
            j = 0;
            unique = 0;
            atleastk = 0;
            int[] count = new int[26];
            while (j < n) {
                if (unique <= a) {
                    idx = s.charAt(j) - 'a';
                    if (count[idx] == 0) {
                        unique++;
                    }
                    count[idx]++;
                    if (count[idx] == k) {
                        atleastk++;
                    }
                    j++;
                } else {
                    idx = s.charAt(i) - 'a';
                    if (count[idx] == k) {
                        atleastk--;
                    }
                    count[idx]--;
                    if (count[idx] == 0) {
                        unique--;
                    }
                    i++;
                }
                if (unique == a && unique == atleastk) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }
}
