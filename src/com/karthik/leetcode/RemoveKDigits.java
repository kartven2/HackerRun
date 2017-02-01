/*
 * Leetcode: https://leetcode.com/problems/remove-k-digits/
 *
 * Given a non-negative integer num represented as a string,
 * remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (num == null || num.trim().isEmpty()) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        char[] input = num.toCharArray();
        while (k-- > 0) {
            Integer prev = null;
            boolean deleted = false;
            for (int i = 0; i < input.length && !deleted; i++) {
                if (prev == null && input[i] == '0') {
                    input[i] = 'd';
                }
                if (input[i] != 'd') {
                    if (prev == null) {
                        prev = i;
                        continue;
                    }
                    if (input[prev] > input[i]) {
                        input[prev] = 'd';
                        deleted = true;
                    }
                    prev = i;
                }
            }
            if (!deleted) {
                input[prev] = 'd';
            }
        }
        StringBuilder sb = new StringBuilder();
        Integer start = null;
        for (int i = 0; i < input.length; i++) {
            if (start == null && input[i] == '0') {
                input[i] = 'd';
            }
            if (input[i] != 'd') {
                start = i;
                sb.append(input[i]);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String... args) {
        RemoveKDigits rk = new RemoveKDigits();
        System.out.println(rk.removeKdigits("10200", 1));
    }
}
