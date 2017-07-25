/*
 * Leetcode: https://leetcode.com/problems/coin-change/
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CoinChange {

    public int coinChange2(int[] a, int amt) {
        if (amt == 0) {
            return 0;
        }
        if (a == null || a.length == 0) {
            return -1;
        }
        Arrays.sort(a);
        Integer[] dp = new Integer[amt + 1];
        dp[0] = 0;
        for (int x : a) {
            if (x <= amt) {
                dp[x] = 1;
            }
        }
        int n = a.length;
        for (int i = 1; i <= amt; i++) {
            if (dp[i] == null) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < n && a[j] <= i; j++) {
                    if (dp[i - a[j]] != null) {
                        dp[i] = Math.min(dp[i], 1 + dp[i - a[j]]);
                    }
                }
                dp[i] = dp[i] == Integer.MAX_VALUE ? null : dp[i];
            }
        }
        return dp[amt] == null ? -1 : dp[amt];
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        int[] cneed = new int[amount + 1];
        Arrays.fill(cneed, -1);
        cneed[0] = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                cneed[coins[i]] = 1;
                set.add(coins[i]);
            }
        }
        for (int i = 1; i <= amount; i++) {
            if (!set.contains(i)) {
                int x = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length && coins[j] <= i; j++) {
                    if (cneed[i - coins[j]] > -1) {
                        x = Math.min(x, 1 + cneed[i - coins[j]]);
                    }
                }
                cneed[i] = x == Integer.MAX_VALUE ? -1 : x;
            }
        }
        return cneed[amount];
    }
}
