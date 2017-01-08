/*
 * LeetCode: https://leetcode.com/problems/remove-duplicate-letters/
 * Given a string which contains only lowercase letters,
 * remove duplicate letters so that every letter appear once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example:
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveDuplicateLetters {

    public static void main(String... args) {
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        System.out.println(rdl.removeDuplicateLetters("bcabc"));
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.trim().isEmpty()) {
            return s;
        }

        int[] count = new int[26];
        int n = s.length();
        char[] ip = s.toCharArray();

        for (int i = 0; i < n; i++) {
            count[ip[i] - 'a']++;
        }

        boolean[] cflag = new boolean[26];
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            int charIdx = ip[i] - 'a';
            if (stk.isEmpty()) {
                stk.push(ip[i]);
                cflag[charIdx] = true;
            } else if (stk.peek() < ip[i]) {
                if (!cflag[charIdx]) {
                    stk.push(ip[i]);
                    cflag[charIdx] = true;
                }
            } else if (stk.peek() > ip[i]) {
                if (!cflag[charIdx]) {
                    while (!stk.isEmpty() && stk.peek() > ip[i] && count[stk.peek() - 'a'] > 0) {
                        cflag[stk.peek() - 'a'] = false;
                        stk.pop();
                    }
                    stk.push(ip[i]);
                    cflag[charIdx] = true;
                }
            }
            count[charIdx]--;
        }

        int sz = stk.size();
        char[] ans = new char[sz];
        while (!stk.isEmpty()) {
            ans[--sz] = stk.pop();
        }

        return new String(ans);
    }
}
