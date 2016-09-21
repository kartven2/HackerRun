/*
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 *
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int a, int b) {
        if (a == 0) {
            return "0";
        }
        if (b == 0) {
            return "";
        }
        long x = (long) a, y = (long) b;
        final String sign = x < 0 ^ y < 0 ? "-" : "";
        x = Math.abs(x);
        y = Math.abs(y);
        final StringBuffer sb = new StringBuffer();
        sb.append(sign);
        long div = x / y, rem = x % y;
        sb.append(div);
        if (rem == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (!map.containsKey(rem)) {
            map.put(rem, sb.length());
            sb.append(rem * 10 / y);
            rem = rem * 10 % y;
        }
        sb.insert(map.get(rem), "(");
        sb.append(")");
        return sb.toString().replace("(0)", "");
    }

    public static void main(String... args) {
        FractionToRecurringDecimal frd = new FractionToRecurringDecimal();
        System.out.println(frd.fractionToDecimal(2, 3));
    }
}
