/*
 * LeetCode problem :
 * https://leetcode.com/problems/self-crossing/
 * Write a one-pass algorithm with O(1) extra
 * space to determine, if your path crosses itself, or not.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] a) {
        if (a == null || a.length <= 3) {
            return false;
        }
        int n = a.length;
        int x = 0, y = 0;
        for (int i = 3; i < n; i++) {
            if (a[i - 3] >= a[i - 1] && a[i] >= a[i - 2]) {
                return true;
            }
            if (i >= 4 && a[i - 4] + a[i] >= a[i - 2] && a[i - 3] == a[i - 1]) {
                return true;
            }
            if (i >= 5 && a[i - 5] <= a[i - 3] && a[i - 4] <= a[i - 2]
                    && a[i - 1] <= a[i - 3] && a[i] <= a[i - 2]
                    && a[i - 4] + a[i] >= a[i - 2] && a[i - 5] + a[i - 1] >= a[i - 3]) {
                return true;
            }

        }
        return false;
    }
}
