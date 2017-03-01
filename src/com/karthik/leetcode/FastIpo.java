/*
 * Leetcode: https://leetcode.com/problems/ipo/
 * Suppose LeetCode will start its IPO soon.
 * In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital
 * before the IPO. Since it has limited resources,
 * it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital
 * after finishing at most k distinct projects.
 * You are given several projects. For each project i, it has a pure profit Pi
 * and a minimum capital of Ci is needed to start the corresponding project.
 * Initially, you have W capital. When you finish a project, you will obtain
 * its pure profit and the profit will be added to your total capital.
 * To sum up, pick a list of at most k distinct projects from given projects
 * to maximize your final capital, and output your final maximized capital.
 */
package com.karthik.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastIpo {

    class Stock {

        private int p;
        private int c;

        Stock(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] p, int[] c) {
        if (p == null || c == null || p.length == 0 || c.length == 0 || p.length != c.length) {
            return 0;
        }
        int n = p.length, ans = w;
        k = k > n ? n : k;
        PriorityQueue<Stock> minCost = new PriorityQueue<>(n, new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                return a.c > b.c ? 1 : a.c < b.c ? -1 : 0;
            }
        });

        PriorityQueue<Stock> maxProfit = new PriorityQueue<>(n, new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                return a.p > b.p ? -1 : a.p < b.p ? 1 : 0;
            }
        });
        for (int i = 0; i < n; i++) {
            minCost.add(new Stock(p[i], c[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCost.isEmpty() && ans >= minCost.peek().c) {
                maxProfit.add(minCost.poll());
            }
            if (!maxProfit.isEmpty()) {
                ans += maxProfit.poll().p;
            }
        }
        return ans;
    }
}
