/*
 * LeetCode Problem: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/ 
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumMovesToEqualArrayElementsII {

    public int minMoves2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Arrays.sort(a);
        int n = a.length, i = 0, j = n - 1, ans = 0;
        while (i < j) {
            ans += (a[j--] - a[i++]);
        }
        return ans;
    }
}
