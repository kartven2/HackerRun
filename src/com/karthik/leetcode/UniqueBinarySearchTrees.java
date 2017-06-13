/*
 * LeetCode: https://leetcode.com/problems/unique-binary-search-trees/#/description
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        if (n < 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return count(dp, n);
    }

    private int count(int[] dp, int n) {
        if (n < 0) {
            return 0;
        }
        if (n < 2) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i - 1] == -1) {
                dp[i - 1] = count(dp, i - 1);
            }
            if (dp[n - i] == -1) {
                dp[n - i] = count(dp, n - i);
            }
            sum += dp[i - 1] * dp[n - i];
        }
        return sum;
    }
}
