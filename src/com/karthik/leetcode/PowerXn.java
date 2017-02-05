/*
 * Leetcode: https://leetcode.com/problems/powx-n/
 *
 * Implement pow(x, n).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PowerXn {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        long e = n < 0 ? -((long) n) : (long) n;
        double r = 1;
        while (e > 1) {
            if (e % 2 > 0) {
                r *= x;
            }
            e >>= 1;
            x *= x;
        }
        r *= x;
        return n < 0 ? (1 / r) : r;
    }
}
