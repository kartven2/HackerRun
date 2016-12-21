/*
 * Leetcode: https://leetcode.com/problems/maximal-rectangle/
 *
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 6.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximalRectangle {

    private int maxRectAreaInHistogram(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int i = 0, n = a.length, tp = 0, ans = -1;
        Stack<Integer> stk = new Stack<>();
        while (i < n) {
            if (stk.isEmpty() || a[i] >= a[stk.peek()]) {
                stk.push(i++);
            } else {
                tp = stk.pop();
                int width = stk.isEmpty() ? i : i - stk.peek() - 1;
                ans = Math.max(ans, width * a[tp]);
            }
        }
        while (!stk.isEmpty()) {
            tp = stk.pop();
            int width = stk.isEmpty() ? i : i - stk.peek() - 1;
            ans = Math.max(ans, width * a[tp]);
        }
        return ans;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length, ans = 0;
        int[] aux = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    aux[j]++;
                } else {
                    aux[j] = 0;
                }
            }
            ans = Math.max(ans, maxRectAreaInHistogram(aux));
        }
        return ans;
    }
}
