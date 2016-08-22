/*
 * Leetcode: https://leetcode.com/problems/create-maximum-number/
 *
 * Given two arrays of length m and n with digits 0-9 representing
 * two numbers. Create the maximum number of length k <= m + n from
 * digits of the two. The relative order of the digits from the same
 * array must be preserved. Return an array of the k digits.
 * You should try to optimize your time and space complexity.
 * 
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CreateMaximumNumber {

    public static void main(String... args) {
        CreateMaximumNumber cmn = new CreateMaximumNumber();
        int[] result = cmn.maxNumber(new int[]{9, 1, 2, 5, 8, 3}, new int[]{3, 4, 6, 5}, 5);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private boolean greater(int[] x, int i, int[] y, int j) {
        int n = x.length, m = y.length;
        for (; i < n && j < m && x[i] == y[j]; i++, j++);
        return (i < n && j < m) ? x[i] > y[j] : j == m;
    }

    private int[] maxNumber(int[] x, int k) {
        int[] result = new int[k];
        int n = x.length, sp = 0;
        for (int i = 0; i < n; i++) {
            for (; sp > 0 && sp + n - i - 1 >= k && x[i] > result[sp - 1];) {
                sp--;
            }
            if (sp < k) {
                result[sp++] = x[i];
            }
        }
        return result;
    }

    private int[] merge(int[] x, int[] y, int k) {
        int n = x.length, m = y.length;
        int[] result = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++) {
            result[r] = greater(x, i, y, j) ? x[i++] : y[j++];
        }
        return result;
    }

    public int[] maxNumber(int[] x, int[] y, int k) {
        int n = x.length, m = y.length;
        int[] result = new int[k];
        for (int i = Math.max(0, k - m); i <= n && i <= k; i++) {
            int[] tmp = merge(maxNumber(x, i), maxNumber(y, k - i), k);
            result = greater(result, 0, tmp, 0) ? result : tmp;
        }
        return result;
    }
}
