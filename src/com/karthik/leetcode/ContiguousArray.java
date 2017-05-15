/*
 * LeetCode: https://leetcode.com/problems/contiguous-array/#/description
 * Given a binary array, find the maximum length of a contiguous subarray
 * with equal number of 0 and 1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ContiguousArray {

    public int findMaxLength(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, cnt = 0, max = 0, st = 0;
        boolean dir = a[0] == 1;
        for (int i = 0; i < n; i++) {
            cnt = a[i] == 1 ? cnt + 1 : cnt - 1;
            if (!(dir && a[i] == 1)) {
                dir = !dir;
                st++;
                if (cnt == 0) {
                    if (max < 2 * st) {
                        max = 2 * st;
                    }
                    st = 0;
                }
            }
        }
        return max;
    }
}
