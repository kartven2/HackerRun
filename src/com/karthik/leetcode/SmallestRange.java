/*
 * Leetcode: https://leetcode.com/problems/smallest-range/#/description
 *
 * You have k lists of sorted integers in ascending order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 */
package com.karthik.leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SmallestRange {

    class Point {

        int value;
        int id;

        Point(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int[] ans = new int[2];
        if (nums == null || nums.isEmpty()) {
            return ans;
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.value - b.value;
            }
        });
        int n = nums.size(), st = -1, end = -1, diff = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] itr = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = nums.get(i);
            if (list.isEmpty()) {
                return ans;
            }
            int val = list.get(0);
            max = Math.max(max, val);
            pq.add(new Point(i, val));
        }
        while (pq.size() == n) {
            Point p = pq.poll();
            if (max - p.value < diff) {
                diff = max - p.value;
                st = p.value;
                end = max;
            }
            if (++itr[p.id] < nums.get(p.id).size()) {
                p.value = nums.get(p.id).get(itr[p.id]);
                max = Math.max(max, p.value);
                pq.offer(p);
            }
        }
        ans[0] = st;
        ans[1] = end;
        return ans;
    }
}