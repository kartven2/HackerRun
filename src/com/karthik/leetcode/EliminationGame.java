/*
 * Leetcode: https://leetcode.com/problems/elimination-game/description/
 *
 * There is a list of sorted integers from 1 to n. Starting from left to right,
 * remove the first number and every other number afterward until you reach the end of the list.
 * Repeat the previous step again, but this time from right to left,
 * remove the right most number and every other number from the remaining numbers.
 * We keep repeating the steps again, alternating left to right and right to left,
 * until a single number remains.
 * Find the last number that remains starting with a list of length n.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EliminationGame {

    public static void main(String... args) {
        EliminationGame eg = new EliminationGame();
        System.out.println(eg.lastRemaining(51));
    }

    public int lastRemaining(int n) {
        if (n <= 0) {
            return 0;
        }
        int lo = 1, hi = n, pow = 1;
        boolean st = true;
        while (n > 1) {
            if ((n & 1) == 1) {
                hi -= pow;
                lo += pow;
            } else if (st) {
                lo += pow;
            } else {
                hi -= pow;
            }
            st = !st;
            pow <<= 1;
            n >>= 1;
        }
        return lo;
    }
}
