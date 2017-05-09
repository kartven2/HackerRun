/*
 * Leetcode: https://leetcode.com/problems/triangle/#/description
 *
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Triangle {

    public static void main(String... args) {
        Triangle tr = new Triangle();
        List<List<Integer>> sub = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        x.add(2);
        sub.add(x);
        x = new ArrayList<>();
        x.add(3);
        x.add(4);
        sub.add(x);
        x = new ArrayList<>();
        x.add(6);
        x.add(5);
        x.add(9);
        sub.add(x);
        x = new ArrayList<>();
        x.add(4);
        x.add(4);
        x.add(8);
        x.add(0);
        sub.add(x);
        tr.minimumTotal(sub);
    }

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
            dp[0] += list.get(0);
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

    class Pair {

        private int val;
        private int i;

        Pair(int val, int i) {
            this.val = val;
            this.i = i;
        }
    }

    public int minimumTotal2(List<List<Integer>> a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }
        int n = a.size();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(a.get(0).get(0), 0));
        int lvl = 1;
        while (lvl < a.size()) {
            Pair p = q.remove();
            List<Integer> w = a.get(lvl);
            q.add(new Pair(p.val + w.get(p.i), p.i));
            q.add(new Pair(p.val + w.get(p.i + 1), p.i + 1));
            if (p.i + 1 == w.size() - 1) {
                lvl++;
            }
        }
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            min = Math.min(min, q.remove().val);
        }
        return min;
    }

}
