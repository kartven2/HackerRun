/*
 * https://leetcode.com/problems/super-ugly-number/#/description
 *
 * Write a program to find the nth super ugly number.
 *
 * Super ugly numbers are positive numbers whose all prime factors
 * are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence
 * of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 *
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * (4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] p) {
        if (n <= 0) {
            return 0;
        }
        int[] k = new int[n];
        k[0] = 1;
        int m = p.length, min;
        int[] counter = new int[m];
        for (int i = 1; i < n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (min > p[j] * k[counter[j]]) {
                    min = p[j] * k[counter[j]];
                }
            }
            for (int j = 0; j < m; j++) {
                if (min == p[j] * k[counter[j]]) {
                    counter[j]++;
                }
            }
            k[i] = min;
        }
        return k[n - 1];
    }
}
