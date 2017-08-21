/*
 * LeetCode Problem: https://leetcode.com/problems/next-greater-element-ii/description/
 * Given a circular array (the next element of the last element is the first element of the
 * array), print the Next Greater Number for every element. The Next Greater Number of a number
 * x is the first greater number to its traversing-order next in the array, which means you could
 * search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        int n = a.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int num = a[i % n];
            while (!stk.isEmpty() && a[stk.peek()] < num) {
                ans[stk.pop()] = num;
            }
            if (i < n) {
                stk.push(i);
            }
        }
        return ans;
    }
}
