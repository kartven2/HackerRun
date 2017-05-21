/*
 * LeetCode: https://leetcode.com/problems/container-with-most-water/#/description
 * 
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ContainerWithMostWater {

    public static void main(String... args) {
        ContainerWithMostWater cmw = new ContainerWithMostWater();
        int[] a = {2, 1};
        cmw.maxArea(a);
    }

    public int maxArea(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, lp = 0, rp = 0, max = Integer.MIN_VALUE, lmax = a[0], rmax = a[n - 1];
        int[] lpos = new int[n];
        int[] rpos = new int[n];
        lpos[lp++] = 0;
        rpos[rp++] = n - 1;
        for (int i = 1; i < n; i++) {
            if (a[i] > lmax) {
                lpos[lp++] = i;
                lmax = a[i];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] > rmax) {
                rpos[rp++] = i;
                rmax = a[i];
            }
        }
        int i = lp;
        while (--i >= 0) {
            int j = 0;
            while (j < rp && rpos[j] > lpos[i]) {
                int w = rpos[j] - lpos[i];
                int h = Math.min(a[rpos[j]], a[lpos[i]]);
                if (w * h > max) {
                    max = w * h;
                }
                j++;
            }
        }
        if (max == Integer.MIN_VALUE) {
            int w = rpos[rp - 1] - lpos[lp - 1];
            int h = Math.min(a[rpos[rp - 1]], a[lpos[lp - 1]]);
            if (w * h > max) {
                max = w * h;
            }
        }
        return max;
    }
}
