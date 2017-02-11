/*
 * https://leetcode.com/problems/number-of-digit-one/
 *
 * Given an integer n, count the total number of digit 1
 * appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NumberOfDigitOne {

    private static final int[] DP = new int[10];

    public int countDigitOne(int n) {
        for (int j = 1, k = 0; j < 10; k = (k * 10) + 9, j++) {
            DP[j] = DP[j - 1] * 10 + (k + 1);
        }
        return eval(n);
    }

    private int eval(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 9) {
            return 1;
        }
        long mm = 1;
        for (; mm <= n; mm *= 10);
        mm /= 10;
        int m = (int) mm;
        int dpIdx = Integer.toString(m - 1).length(), fd = n / m;
        int ans = DP[dpIdx] * fd;
        ans += fd > 1 ? m : fd == 1 ? (n % m) + 1 : 0;
        ans += eval(n % m);
        return ans;
    }

    public static void main(String... args) {
        NumberOfDigitOne ndo = new NumberOfDigitOne();
        System.out.println(ndo.countDigitOne(1410065408));
    }
}
