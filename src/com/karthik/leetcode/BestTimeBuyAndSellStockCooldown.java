/*
 * LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * Say you have an array for which the ith element is the price of a
 * given stock on day i. Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with
 * the following restrictions:
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day.
 * (ie, cooldown 1 day)
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BestTimeBuyAndSellStockCooldown {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        Integer[] sp = new Integer[2];
        return eval(null, prices, n, 0, 0);
    }

    private int eval(Integer x, int[] prices, int n, int ans, int i) {
        if (i >= n) {
            return ans;
        }
        if (x == null) {
            return eval(prices[i], prices, n, ans, i + 1);
        }
        if (x < prices[i]) {
            return Math.max(eval(null, prices, n, ans + (prices[i] - x), i + 2), eval(x, prices, n, ans, i + 1));
        }
        if (x > prices[i]) {
            return eval(prices[i], prices, n, ans, i + 1);
        }
        return eval(x, prices, n, ans, i + 1);
    }

    public static void main(String... args) {
        BestTimeBuyAndSellStockCooldown btbssc = new BestTimeBuyAndSellStockCooldown();
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(btbssc.maxProfit(prices));
    }
}
