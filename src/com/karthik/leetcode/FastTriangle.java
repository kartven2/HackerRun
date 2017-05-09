/*
 * LeetCode: https://leetcode.com/problems/triangle/#/description
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastTriangle {

    public int minimumTotal(List<List<Integer>> a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }
        int n = a.size(), pre, curr;
        int[] dp = new int[n];
        dp[0] = a.get(0).get(0);
        for (int i = 1; i < n; i++) {
            pre = dp[0];
            List<Integer> list = a.get(i);
            dp[0] = list.get(0) + pre;
            for (int j = 1; j < i; j++) {
                curr = dp[j];
                dp[j] = list.get(j) + Math.min(pre, curr);
                pre = curr;
            }
            dp[i] = list.get(i) + pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[i] < ans) {
                ans = dp[i];
            }
        }
        return ans;
    }
}
