/*
 * LeetCode: https://leetcode.com/problems/integer-break/#/description
 * Given a positive integer n, break it into the sum of at least two positive
 * integers and maximize the product of those integers. Return the maximum product you can get.
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IntegerBreak {

    public static void main(String... args) {
        IntegerBreak ib = new IntegerBreak();
        int b = ib.integerBreak(10);
        System.out.println(b);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int integerBreak(int n) {
        if (n < 2) {
            return 1;
        }
        int[] a = new int[n + 1];
        a[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                a[i] = max(a[i], max(j, a[j]) * max(i - j, a[i - j]));
            }
        }
        return a[n];
    }
}
