/*
 * LeetCode: https://leetcode.com/problems/bitwise-and-of-numbers-range/#/description
 * Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BitwiseAndNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) {
            return m;
        }
        if (m == 0 || n == 0 || (m & n) == 0) {
            return 0;
        }
        int ans = m & n, diff = n - m, curr = ans, shift = 1, pow = 0;
        while (curr > 0) {
            //if current bit is 1 and diff is greater than 2^x then flip the bit.
            if ((curr & 1) > 0 && diff > (1 << pow)) {
                ans = ans ^ shift;
            }
            curr >>= 1;
            shift <<= 1;
            pow++;
        }
        return ans;
    }

    public static void main(String... args) {
        BitwiseAndNumbersRange ba = new BitwiseAndNumbersRange();
        ba.rangeBitwiseAnd(1, 3);
    }
}
