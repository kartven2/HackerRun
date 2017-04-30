/*
 * Leetcode: https://leetcode.com/problems/super-washing-machines/#/description
 *
 * You have n super washing machines on a line
 * Initially, each washing machine has some dresses or is empty.
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines,
 * and pass one dress of each washing machine to one of its adjacent
 * washing machines at the same time .
 * Given an integer array representing the number of dresses in each washing
 * machine from left to right on the line, you should find the minimum number
 * of moves to make all the washing machines have the same number of dresses.
 * If it is not possible to do it, return -1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SuperWashingMachines {

    public int findMinMoves(int[] a) {
        if (a == null || a.length < 2) {
            return 0;
        }
        int n = a.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % n > 0) {
            return -1;
        }
        int mean = (int) (sum / n);
        int[] diff = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            diff[i] = a[i] - mean;
            if (diff[i] > max) {
                max = diff[i];
            }
        }
        max = Math.abs(diff[0]) > max ? Math.abs(diff[0]) : max;
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
            if (Math.abs(diff[i]) > max) {
                max = Math.abs(diff[i]);
            }
        }
        return max;
    }
}
