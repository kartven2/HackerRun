/*
 * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/#/description
 *
 * Given a positive integer n, find the number of non-negative integers
 * less than or equal to n, whose binary representations do NOT contain consecutive ones.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NonNegativeIntegersConsecutiveOnes {

    public int findIntegers(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] fib = new int[32];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 2; i < 32; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        int b = 30, prevBit = 0, ans = 0;
        while (b >= 0) {
            if ((n & (1 << b)) > 0) {
                ans += fib[b];
                if (prevBit == 1) {
                    return ans;
                }
                prevBit = 1;
            } else {
                prevBit = 0;
            }
            b--;
        }
        return ans + 1;
    }
}
