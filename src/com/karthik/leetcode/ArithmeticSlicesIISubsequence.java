/*
 * LeetCode: https://leetcode.com/problems/arithmetic-slices-ii-subsequence/#/description
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array
 * is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic.
 * In particular, this means that k ≥ 2.
 * The function should return the number of arithmetic subsequence slices in the array A.
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000.
 * The output is guaranteed to be less than 231-1.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ArithmeticSlicesIISubsequence {

    public static void main(String... args) {
        ArithmeticSlicesIISubsequence as = new ArithmeticSlicesIISubsequence();
        System.out.println(as.numberOfArithmeticSlices(new int[]{2, 2, 2, 2}));
    }

    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int n = a.length, ans = 0;
        Map<Integer, Integer>[] map = (Map<Integer, Integer>[]) new HashMap[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) a[i] - (long) a[j];
                if (diff >= Integer.MAX_VALUE || diff <= Integer.MIN_VALUE) {
                    continue;
                }
                int d = (int) diff;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                ans += c2;
                map[i].put(d, c1 + c2 + 1);
            }
        }
        return ans;
    }
}
