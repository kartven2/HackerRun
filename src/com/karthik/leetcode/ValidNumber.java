/*
 * LeetCode: https://leetcode.com/problems/valid-number/#/description
 *
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidNumber {

    public static void main(String... args) {
        ValidNumber v = new ValidNumber();
        v.isNumber("e");
    }

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        int n = s.length();
        if (n == 0) {
            return false;
        }
        boolean decimal = false;
        boolean exp = false;
        boolean digit = false;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                if ((c == '+' || c == '-') && (i == 0 || (i > 0 && i < n - 1 && s.charAt(i - 1) == 'e'))) {
                    continue;
                }
                if (c == '.' && !decimal && !exp) {
                    decimal = true;
                    continue;
                }
                if (c == 'e' && !exp && i < n - 1 && i > 0 && digit) {
                    exp = true;
                    continue;
                }
                return false;
            } else if (!digit) {
                digit = true;
            }
        }
        return digit;
    }
}
