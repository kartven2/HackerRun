/*
 * LeetCode: https://leetcode.com/problems/product-of-array-except-self/#/description
 *
 * Given an array of n integers where n > 1, nums,
 * return an array output such that output[i] is equal to the product of all
 * the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        int tmp = 1, n = a.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = 0; i < n; i++) {
            res[i] *= tmp;
            tmp *= a[i];
        }
        tmp = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= tmp;
            tmp *= a[i];
        }
        return res;
    }

    public int[] productExceptSelf2(int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        int n = a.length;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = a[0];
        r[n - 1] = a[n - 1];
        for (int i = 1; i < n; i++) {
            l[i] = (a[i] * l[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            r[i] = (a[i] * r[i + 1]);
        }
        int[] res = new int[n];
        res[0] = r[1];
        res[n - 1] = l[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = (l[i - 1] * r[i + 1]);
        }
        return res;
    }
}
