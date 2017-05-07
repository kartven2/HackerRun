/*
 * LeetCode Problem: https://leetcode.com/problems/longest-uncommon-subsequence-ii/#/description
 * Given a list of strings, you need to find the longest uncommon subsequence among them.
 * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements.
 * Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence.
 * If the longest uncommon subsequence doesn't exist, return -1.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestUncommonSubsequenceII {

    private boolean isSubSequence(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == b.length();
    }

    public int findLUSlength(String[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        Set<String> set = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (!set.add(a[i])) {
                duplicates.add(a[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (!duplicates.contains(a[i])) {
                if (i == 0) {
                    return a[i].length();
                }
                for (int j = 0; j < i; j++) {
                    if (isSubSequence(a[j], a[i])) {
                        break;
                    }
                    if (j == i - 1) {
                        return a[i].length();
                    }
                }
            }
        }
        return -1;
    }

    private void genSeq(String s, boolean[] mark, int i, Set<String> set, Set<String> dup) {
        if (i == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                if (mark[j]) {
                    sb.append(s.charAt(j));
                }
            }
            boolean added = set.add(sb.toString());
            if (!added) {
                dup.add(sb.toString());
            }
            return;
        }
        mark[i] = true;
        genSeq(s, mark, i + 1, set, dup);
        mark[i] = false;
        genSeq(s, mark, i + 1, set, dup);
    }

    public int findLUSlength2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int n = strs.length;
        Set<String> set = new HashSet<>();
        Set<String> dup = new HashSet<>();
        for (int i = 0; i < n; i++) {
            genSeq(strs[i], new boolean[strs[i].length()], 0, set, dup);
        }
        int max = 0;
        for (String x : set) {
            if (dup.add(x) && x.length() > max) {
                max = x.length();
            }
        }
        return max == 0 ? -1 : max;
    }
}
