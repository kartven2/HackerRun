/*
 * Leetcode: https://leetcode.com/problems/super-pow/#/description
 * Your task is to calculate ab mod 1337 where a is a positive
 * integer and b is an extremely large positive integer given in the form of an array.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SuperPow {

    private static final int M = 1337;

    private int pow(int a, int n) {
        if (n == 0) {
            return 1;
        }
        a %= M;
        int r = 1;
        while (n > 1) {
            if ((n & 1) == 1) {
                r = (r * a) % M;
            }
            a = (a * a) % M;
            n >>= 1;
        }
        return (r * a) % M;
    }

    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) {
            return 0;
        }
        int n = b.length, res = 1;
        for (int i = n - 1; i >= 0; i--) {
            res = (res * pow(a, b[i])) % M;
            a = pow(a, 10) % M;
        }
        return res;
    }
}
