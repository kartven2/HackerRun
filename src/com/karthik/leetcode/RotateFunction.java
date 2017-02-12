/*
 * Leetcode: https://leetcode.com/problems/rotate-function/
 *
 * Given an array of integers A and let n to be its length.
 * Assume Bk to be an array obtained by rotating the array A
 * k positions clock-wise, we define a "rotation function" F on A as follow:
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
 * Note:
 * n is guaranteed to be less than 105.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RotateFunction {

    public int maxRotateFunction(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            ans += i * a[i];
        }
        int val = ans;
        for (int i = n - 1; i > 0; i--) {
            val = val + (int) (sum - a[i]) - (n - 1) * a[i];
            ans = Math.max(ans, val);
        }
        return ans;
    }
}
