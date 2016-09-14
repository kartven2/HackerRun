/*
 * https://leetcode.com/problems/divide-two-integers/
 *
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            return dividend > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0, shift;
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
        for (; a >= b;) {
            shift = 0;
            for (; a >= (b << shift);) {
                shift++;
            }
            shift--;
            a -= (b << shift);
            result += (1 << shift);
        }
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            return -result;
        }
        return result;
    }

    public static void main(String... args) {
        DivideTwoIntegers dti = new DivideTwoIntegers();
        System.out.println(dti.divide(2147483647, 2));

    }
}
