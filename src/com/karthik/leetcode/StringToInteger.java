/*
 * LeetCode: https://leetcode.com/problems/string-to-integer-atoi/#/description
 *
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely
 * (ie, no given input specs). You are responsible to gather all the input requirements up front.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class StringToInteger {

    public static void main(String... args) {
        StringToInteger sti = new StringToInteger();
        sti.myAtoi("9223372036854775809");
    }

    public int myAtoi(String a) {
        double ans = 0;
        if (a == null || a.trim().length() == 0) {
            return (int) ans;
        }
        char[] c = a.toCharArray();
        int i = 0, n = a.length();
        while (i < n && c[i] == ' ') {
            i++;
        }
        boolean minus = c[i] == '-';
        if (minus || c[i] == '+') {
            i++;
        }
        boolean first = true;
        while (i < n && c[i] >= '0' && c[i] <= '9') {
            int x = c[i++] - '0';
            if (first) {
                ans = x;
                first = false;
            } else {
                ans = (ans * 10) + x;
            }
        }
        if (minus) {
            ans *= (-1);
        }
        return ans >= Integer.MAX_VALUE ? Integer.MAX_VALUE : ans <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) ans;
    }
}
