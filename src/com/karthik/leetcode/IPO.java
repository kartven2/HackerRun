/*
 * Leetcode: https://leetcode.com/problems/ipo/
 * Suppose LeetCode will start its IPO soon.
 * In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO.
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * You are given several projects. For each project i, it has a pure profit Pi
 * and a minimum capital of Ci is needed to start the corresponding project.
 * Initially, you have W capital. When you finish a project, you will obtain its pure profit
 * and the profit will be added to your total capital.
 * To sum up, pick a list of at most k distinct projects from given
 * projects to maximize your final capital, and output your final maximized capital.
 * You may assume all numbers in the input are non-negative integers.
 * The length of Profits array and Capital array will not exceed 50,000.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IPO {

    class StockComparator implements Comparator<Stock> {

        @Override
        public int compare(Stock a, Stock b) {
            if (a == b || (a.capital == b.capital && a.profit == b.profit)) {
                return 0;
            }
            if (a.profit != b.profit) {
                return (a.profit > b.profit) ? -1 : (a.profit < b.profit) ? 1 : 0;
            }
            return (a.capital > b.capital) ? 1 : (a.capital < b.capital) ? -1 : 0;
        }
    }

    class Stock {

        private int profit;
        private int capital;

        Stock(int p, int c) {
            profit = p;
            capital = c;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] p, int[] c) {
        if (p == null || c == null || p.length == 0 || c.length == 0 || p.length != c.length) {
            return 0;
        }
        int n = p.length;
        k = k > n ? n : k;
        List<Stock> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Stock(p[i], c[i]));
        }
        Collections.sort(list, new StockComparator());
        int ans = w;
        for (int i = 0; i < k; i++) {
            Iterator<Stock> itr = list.iterator();
            while (itr.hasNext()) {
                Stock x = itr.next();
                if (x.capital <= ans) {
                    ans += x.profit;
                    itr.remove();
                    break;
                }
            }
        }
        return ans;
    }
}
