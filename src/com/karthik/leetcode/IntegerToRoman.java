/*
 * Leetcode: https://leetcode.com/problems/integer-to-roman/#/description
 *
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IntegerToRoman {

    public static void main(String... args) {
        IntegerToRoman ir = new IntegerToRoman();
        System.out.println(ir.intToRoman(199));
    }

    public String intToRoman(int a) {
        String[] romans = {"N", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        if (a <= 0 || a > 3999) {
            return romans[0];
        }
        int[] value = {0, 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        StringBuilder sb = new StringBuilder();
        for (int i = value.length - 1; i > 0; i--) {
            while (a >= value[i]) {
                a -= value[i];
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }
}
