/*
 * LeetCode Problem : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/#/description
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
*/
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BestTimeToBuyAndSellStockIV {

    public static void main(String... args) {
        BestTimeToBuyAndSellStockIV btb = new BestTimeToBuyAndSellStockIV();
        System.out.println(btb.maxProfit(2, new int[]{2, 1, 4}));
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int maxProfit2(int k, int[] a) {
        if (a == null || a.length < 2) {
            return 0;
        }
        int n = a.length;
        if (k >= n / 2) {
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (a[i] > a[i - 1]) {
                    max += a[i] - a[i - 1];
                }
            }
            return max;
        }
        int[] cash = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(sell, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) {
                cash[j] = max(cash[j], sell[j] + a[i]);
                sell[j] = max(sell[j], cash[j - 1] - a[i]);
            }
        }
        return cash[k];
    }

    public int maxProfit(int k, int[] a) {
        if (a == null || a.length < 2) {
            return 0;
        }
        if (k >= a.length) {
            int max = 0;
            for (int i = 1; i < a.length; i++) {
                if (a[i] > a[i - 1]) {
                    max += (a[i] - a[i - 1]);
                }
            }
            return max;
        }
        return dfs(k, a, 0, 0, null);
    }

    private int max(int a, int b, int c) {
        return a > b && a > c ? a : b > c ? b : c;
    }

    private int dfs(int k, int[] a, int i, int sum, Integer holding) {
        if (k == 0 || i == a.length) {
            return max(0, sum);
        }
        int bit = holding == null ? 0 : 1;
        int buy = 0, sell = 0, hold = 0;
        if (holding == null) {
            buy = dfs(k, a, i + 1, sum, a[i]);
        }
        if (holding != null && a[i] >= holding) {
            sell = dfs(k - 1, a, i + 1, sum + (a[i] - holding), null);
        }
        hold = dfs(k, a, i + 1, sum, holding);
        return max(buy, sell, hold);
    }
}
