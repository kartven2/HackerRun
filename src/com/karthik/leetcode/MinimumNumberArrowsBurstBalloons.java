/*
 * LeetCode problem : https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 * There are a number of spherical balloons spread in two-dimensional space.
 * For each balloon, provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.
 * Start is always smaller than end. There will be at most 104 balloons.
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely.
 * The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumNumberArrowsBurstBalloons {

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int ans = 1, val = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (val >= points[i][0]) {
                continue;
            }
            ans++;
            val = points[i][1];
        }
        return ans;
    }
}
