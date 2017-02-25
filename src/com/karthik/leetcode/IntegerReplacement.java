/*
 * LeetCode: https://leetcode.com/problems/integer-replacement/
 * Given a positive integer n and you can do operations as follow:
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {
        if (n <= 0) {
            return n;
        }
        return ireplaceHelper(n, 0);
    }

    private int ireplaceHelper(long n, int a) {
        if (n <= 1) {
            return a;
        }
        if (n == 2) {
            return a + 1;
        }
        if (n % 2 == 0) {
            return ireplaceHelper(n >> 1, a + 1);
        }
        return Math.min(ireplaceHelper(n - 1, a + 1), ireplaceHelper(n + 1, a + 1));
    }

    public static void main(String... args) {
        IntegerReplacement ir = new IntegerReplacement();
        System.out.println(ir.integerReplacement(18));
    }
}
