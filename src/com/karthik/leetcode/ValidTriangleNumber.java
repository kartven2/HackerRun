/*
 * Leetcode: https://leetcode.com/problems/valid-triangle-number/#/description
 *
 * Given an array consists of non-negative integers, your task is to count the
 * number of triplets chosen from the array that can make triangles if we take
 * them as side lengths of a triangle.
 *
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidTriangleNumber {

    public int triangleNumber2(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        Arrays.sort(a);
        int ans = 0;
        for (int i = a.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (a[l] + a[r] > a[i]) {
                    ans += (r - l);
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ans;
    }

    private int findCountInInterval(int[] a, int lo, int hi, int aidx, int bidx) {
        int val1 = a[bidx] - a[aidx], val2 = a[aidx] + a[bidx];
        while (lo <= hi && a[lo] <= val1) {
            lo++;
        }
        while (lo <= hi && a[hi] >= val2) {
            hi--;
        }
        return lo <= hi ? hi - lo + 1 : 0;
    }

    public int triangleNumber(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int n = a.length, ans = 0;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                ans += findCountInInterval(a, j + 1, n - 1, i, j);
            }
        }
        return ans;
    }
}
