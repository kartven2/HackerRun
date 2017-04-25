/*
 * LeetCode Problem: https://leetcode.com/problems/house-robber-ii/#/description
 * After robbing those houses on that street, the thief has found himself
 * a new place for his thievery so that he will not get too much attention.
 * This time, all houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount
 * of money of each house, determine the maximum amount of money you
 * can rob tonight without alerting the police.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HouseRobberII {

    public int rob2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        if (n < 4) {
            int max = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] > max) {
                    max = a[i];
                }
            }
            return max;
        }
        return Math.max(dp(a, 0, n - 2), dp(a, 1, n - 1));
    }

    private int dp(int[] a, int lo, int hi) {
        int c = 0, pr = 0, pr2 = 0;
        for (int i = lo; i <= hi; i++) {
            c = Math.max(pr2 + a[i], pr);
            pr2 = pr;
            pr = c;
        }
        return c;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, max = -1;
        if (n < 4) {
            for (int i = 0; i < n; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            return max;
        }
        if (n == 4) {
            return Math.max(nums[0] + nums[2], nums[1] + nums[3]);
        }
        int useFirst = 0, useLast = 0, c = 0, pr = 0, pr2 = 0;
        for (int i = 0; i < n - 1; i++) {
            c = Math.max(pr2 + nums[i], pr);
            pr2 = pr;
            pr = c;
        }
        useFirst = c;
        c = 0;
        pr = 0;
        pr2 = 0;
        for (int i = 1; i < n; i++) {
            c = Math.max(nums[i] + pr2, pr);
            pr2 = pr;
            pr = c;
        }
        useLast = c;
        return Math.max(useFirst, useLast);
    }
}
