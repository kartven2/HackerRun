/*
 * LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int b1 = Integer.MIN_VALUE, b2 = Integer.MIN_VALUE, s1 = 0, s2 = 0;
        for (int x : prices) {
            s2 = Math.max(s2, b2 + x);
            b2 = Math.max(b2, s1 - x);
            s1 = Math.max(s1, b1 + x);
            b1 = Math.max(b1, -x);
        }
        return s2;
    }

    public static void main(String... args) {
        BestTimeToBuyAndSellStockIII ba = new BestTimeToBuyAndSellStockIII();
        System.out.println(ba.maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }
}
