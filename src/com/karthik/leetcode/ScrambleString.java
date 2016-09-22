/*
 * https://leetcode.com/problems/scramble-string/
 *
 * Given two strings s1 and s2 of the same length,
 * determine if s2 is a scrambled string of s1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ScrambleString {

    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        int n = s1.length();
        if (n == 1) {
            return s1.equals(s2);
        }

        int[] ch = new int[26];
        for (int i = 0; i < n; i++) {
            ch[s1.charAt(i) - 'a']++;
            ch[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (ch[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);
            if (isScramble(s11, s21) && isScramble(s12, s22)) {
                return true;
            }
            String s23 = s2.substring(0, n - i);
            String s24 = s2.substring(n - i);
            if (isScramble(s11, s24) && isScramble(s12, s23)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String... args) {
        ScrambleString ss = new ScrambleString();
        System.out.println(ss.isScramble("abcdefghijklmnopq", "efghijklmnopqcadb"));
    }
}
