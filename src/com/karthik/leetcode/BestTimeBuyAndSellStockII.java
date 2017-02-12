/*
 * LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BestTimeBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0], max = prices[0], n = prices.length, ans = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > max) {
                max = prices[i];
            } else if (prices[i] < max) {
                ans += max - min;
                max = prices[i];
                min = prices[i];
            }
        }
        ans += max - min;
        return ans;
    }
}
