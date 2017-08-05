/*
 * LeetCode Problem: https://leetcode.com/problems/is-subsequence/description/
 *
  * Given a string s and a string t, check if s is subsequence of t.
 *
 * You may assume that there is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 *
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * 
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one
 * by one to see if T has its subsequence. In this scenario, how would you change your code?
 *
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IsSubsequence {

    public static void main(String... args) {
        Integer[] tmp = {7, 9};
        List<Integer> list = Arrays.asList(tmp);
        System.out.println(Collections.binarySearch(list, 100));
        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.isSubsequence("acb", "ahbgdc"));
    }

    private int nextValue(List<Integer> list, int key) {
        int lo = 0, hi = list.size() - 1, mid = 0;
        while (lo <= hi) {
            mid = lo + ((hi - lo) / 2);
            if (list.get(mid) == key) {
                return key;
            } else if (list.get(mid) < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo >= list.size() ? -1 : list.get(lo);
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null || s.trim().isEmpty()) {
            return true;
        }
        if (t == null || t.trim().isEmpty()) {
            return false;
        }
        List<Integer>[] a = (List<Integer>[]) new LinkedList[26];
        int n = t.length();
        for (int i = 0; i < n; i++) {
            int idx = t.charAt(i) - 'a';
            if (a[idx] == null) {
                a[idx] = new LinkedList<>();
            }
            a[idx].add(i);
        }
        int lastIdx = -1, m = s.length();
        for (int i = 0; i < m; i++) {
            int idx = s.charAt(i) - 'a';
            if (a[idx] == null) {
                return false;
            }
            //lastIdx = nextValue(a[idx], lastIdx+1);
            /*-if (lastIdx == -1) {
                return false;
            }*/
            int pos = Collections.binarySearch(a[idx], lastIdx + 1);
            pos = pos < 0 ? pos == -1 ? 0 : -pos - 1 : pos;
            if (pos == a[idx].size()) {
                return false;
            }
            lastIdx = a[idx].get(pos);
        }
        return true;
    }
}
