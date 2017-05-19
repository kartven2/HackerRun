/*
 * LeetCode: https://leetcode.com/problems/sort-colors/#/description
 * Given an array with n objects colored red, white or blue, sort them
 * so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SortColors {

    public void sortColors(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int[] dp = new int[3];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            dp[a[i]]++;
        }
        for (int i = 0, k = 0; i < n; i++) {
            if (dp[k] > 0) {
                dp[k]--;
                a[i] = k;
            } else {
                k++;
                i--;
            }
        }
    }
}
