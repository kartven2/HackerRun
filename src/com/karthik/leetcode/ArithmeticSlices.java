/*-
 * LeetCode Problem: https://leetcode.com/problems/arithmetic-slices/description/
 * A sequence of number is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 * A zero-indexed array A consisting of N numbers is given.
 * A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * The function should return the number of arithmetic slices in the array A.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ArithmeticSlices {

    public static void main(String... args) {
        ArithmeticSlices as = new ArithmeticSlices();
        as.numberOfArithmeticSlices(new int[]{1, 2, 3, 8, 9, 10});
    }

    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int n = a.length, diff = a[1] - a[0], start = 0;
        List<Integer> list = new LinkedList<>();
        for (int i = 2; i < n; i++) {
            if ((a[i] - a[i - 1]) != diff || (i == n - 1)) {
                int cnt = (a[i] - a[i - 1]) != diff ? i - start : i - start + 1;
                if (cnt > 2) {
                    list.add(cnt - 2);
                }
                start = i - 1;
                diff = a[i] - a[i - 1];
            }
        }
        int sum = 0;
        for (int x : list) {
            sum += ((x * (x + 1)) >> 1);
        }
        return sum;
    }
}