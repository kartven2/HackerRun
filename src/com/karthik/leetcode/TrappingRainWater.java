/*
 * LeetCode Problem : https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TrappingRainWater {

    private static final int MIN = Integer.MIN_VALUE;

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] lmax = new int[n];
        int[] rmax = new int[n];
        int max = MIN;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            lmax[i] = max;
        }
        max = MIN;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            rmax[i] = max;
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (height[i] < lmax[i] && height[i] < rmax[i]) {
                ans += Math.min(lmax[i], rmax[i]) - height[i];
            }
        }
        return ans;
    }
}
