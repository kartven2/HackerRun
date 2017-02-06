/*
 * LeetCode: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle in
 * the histogram.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastLargestRectangleHistogram {

    public int largestRectangleArea(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stk = new Stack<>();
        int i = 0;
        for (; i < h.length;) {
            if (stk.isEmpty() || h[stk.peek()] <= h[i]) {
                stk.push(i++);
            } else {
                int p = stk.pop(), ht = h[p], w = stk.isEmpty() ? i : i - stk.peek() - 1;
                max = Math.max(max, w * ht);
            }
        }
        while (!stk.isEmpty()) {
            int p = stk.pop(), ht = h[p], w = stk.isEmpty() ? i : i - stk.peek() - 1;
            max = Math.max(max, w * ht);
        }
        return max;
    }
}
