/*
 * LeetCode Problem: https://leetcode.com/problems/smallest-good-base/#/description
 *
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 * Now given a string representing n, you should return the smallest good base of
 * n in string format.
 */
package com.karthik.leetcode;

import java.math.BigInteger;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SmallestGoodBase {

    public static void main(String... args) {
        SmallestGoodBase sgb = new SmallestGoodBase();
        sgb.smallestGoodBase("13");
    }

    public String smallestGoodBase(String n) {
        if (n == null || n.trim().length() == 0) {
            return n;
        }
        long num = Long.parseLong(n), base = Long.MAX_VALUE;
        int maxk = (int) (Math.log(num + 1) / Math.log(2));
        for (int k = 2; k <= maxk; k++) {
            long lo = 2, hi = Long.MAX_VALUE;
            while (lo <= hi) {
                long x = lo + (hi - lo) / 2, x1 = x - 1;
                BigInteger lhs = BigInteger.valueOf(num).multiply(BigInteger.valueOf(x1));
                BigInteger rhs = (BigInteger.valueOf(x).pow(k)).subtract(BigInteger.ONE);
                int cmp = lhs.compareTo(rhs);
                if (cmp == 0) {
                    if (x < base) {
                        base = x;
                    }
                    break;
                } else if (cmp < 0) {
                    hi = x - 1;
                } else {
                    lo = x + 1;
                }
            }
        }
        return "" + base;
    }
}
