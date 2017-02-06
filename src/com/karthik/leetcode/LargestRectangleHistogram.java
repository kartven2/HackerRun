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
public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE, i = 0;
        Stack<Integer> pos = new Stack<>();
        while (i < heights.length) {
            if (pos.isEmpty() || heights[i] >= heights[pos.peek()]) {
                pos.push(i++);
            } else {
                int p = pos.pop(), h = heights[p], w = pos.isEmpty() ? i : i - pos.peek() - 1;
                max = Math.max(max, w * h);
            }
        }
        while (!pos.isEmpty()) {
            int p = pos.pop(), h = heights[p], w = pos.isEmpty() ? i : i - pos.peek() - 1;
            max = Math.max(max, w * h);
        }
        return max;
    }

    public static void main(String... args) {
        LargestRectangleHistogram lrh = new LargestRectangleHistogram();
        int[] input = {4, 4, 4, 4, 4, 5, 0, 0, 4, 4, 5, 6, 4, 4, 4, 0};
        System.out.println(lrh.largestRectangleArea(input));
    }
}
