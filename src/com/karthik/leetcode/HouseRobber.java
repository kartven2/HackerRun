/*
 * LeetCode Problem: https://leetcode.com/problems/house-robber/#/description
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have 
 * system connected and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money
 * of each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HouseRobber {

    public int rob(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        if (a.length < 2) {
            return a[0];
        }
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (i > 1) {
                a[i] = Math.max(a[i - 1], a[i - 2] + a[i]);
            } else {
                a[i] = Math.max(a[i - 1], a[i]);
            }
        }
        return a[n - 1];
    }
}
