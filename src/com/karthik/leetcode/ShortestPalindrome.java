/*
 * Leetcode: https://leetcode.com/problems/shortest-palindrome/
 *
 * Given a string S, you are allowed to convert it to a palindrome
 * by adding characters in front of it. Find and return the shortest
 * palindrome you can find by performing this transformation.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ShortestPalindrome {

    public static void main(String... args) {
        ShortestPalindrome sc = new ShortestPalindrome();
        System.out.println(sc.shortestPalindrome("abbacd"));
    }

    private boolean isPalindrome(char[] s, int lo, int hi) {
        int i = lo, j = hi;
        while (i < j) {
            if (s[i++] != s[j--]) {
                return false;
            }
        }
        return true;
    }

    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] sarr = s.toCharArray();
        int n = s.length() - 1;
        int i = s.length() - 1;
        for (; i >= 0; i--) {
            if (isPalindrome(sarr, 0, i)) {
                break;
            }
        }
        i++;
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        while (i <= n) {
            sb.insert(0, sarr[i]);
            i++;
        }
        return sb.toString();
    }
}
