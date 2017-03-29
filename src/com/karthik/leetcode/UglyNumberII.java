/*
 * Leetcode: https://leetcode.com/problems/ugly-number-ii/#/description
 *
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 *
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UglyNumberII {

    public int nthUglyNumber2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] k = new int[n];
        int p2 = 0, p3 = 0, p5 = 0;
        k[0] = 1;
        for (int i = 1; i < n; i++) {
            k[i] = Math.min(k[p2] * 2, Math.min(k[p3] * 3, k[p5] * 5));
            if (k[i] == k[p2] * 2) {
                p2++;
            }
            if (k[i] == k[p3] * 3) {
                p3++;
            }
            if (k[i] == k[p5] * 5) {
                p5++;
            }
        }
        return k[n - 1];
    }

    public int nthUglyNumber(int n) {
        if (n <= 1) {
            return 1;
        }
        PriorityQueue<Long> q = new PriorityQueue<>();
        Set<Long> s = new HashSet<>();
        q.add(1l);
        s.add(1l);
        long x = 1;
        for (int i = 0; i < n; i++) {
            x = q.remove();
            if (i == n - 1) {
                return (int) x;
            }
            long a = x * 2;
            if (s.add(a)) {
                q.add(a);
            }
            a = x * 3;
            if (s.add(a)) {
                q.add(a);
            }
            a = x * 5;
            if (s.add(a)) {
                q.add(a);
            }
        }
        throw new IllegalArgumentException("Already Found");
    }
}
