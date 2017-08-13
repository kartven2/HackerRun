/*
 * LeetCode: https://leetcode.com/problems/fraction-addition-and-subtraction/description/
 * Given a string representing an expression of fraction addition and subtraction,
 * you need to return the calculation result in string format. The final result should be irreducible fraction.
 * If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1.
 * So in this case, 2 should be converted to 2/1.
 * 
 */
package com.karthik.leetcode;

import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FractionAdditionAndSubtraction {

    private int gcd(int a, int b) {
        return a == 0 ? b : b == 0 ? a : gcd(b, a % b);
    }

    public String fractionAddition(String s) {
        if (s == null || s.trim().length() == 0) {
            return s;
        }
        Scanner sc = new Scanner(s).useDelimiter("/|(?=[+-])");
        int c = 0, d = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt(), b = sc.nextInt();
            c = (c * b) + (a * d);
            d = (d * b);
            int gcd = gcd(Math.abs(c), Math.abs(d));
            c /= gcd;
            d /= gcd;
        }
        return c + "/" + d;
    }
}