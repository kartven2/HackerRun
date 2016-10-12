/*
 * Leetcode: https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()",
 * which has length = 4.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = -1, n = s.length(), ans = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (st.isEmpty()) {
                left = i;
            } else {
                st.pop();
                ans = st.isEmpty() ? Math.max(ans, i - left) : Math.max(ans, i - st.peek());
            }
        }
        return ans;
    }
}
