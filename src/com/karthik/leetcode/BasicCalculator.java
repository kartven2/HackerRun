/*
 * LeetCode: https://leetcode.com/problems/basic-calculator/
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BasicCalculator {

    private int prec(char c) {
        switch (c) {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    private long calc(char op, long v1, long v2) {
        switch (op) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            default:
                throw new IllegalArgumentException("Bad operator");
        }
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Long> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();
        char[] sa = s.toCharArray();
        for (int i = 0; i < sa.length;) {
            if (sa[i] == ' ') {
                i++;
                continue;
            }
            if (sa[i] != ' ' && sa[i] != '(' && sa[i] != ')' && sa[i] != '+' && sa[i] != '-') {
                StringBuilder sb = new StringBuilder();
                while (i < sa.length && sa[i] != ' ' && sa[i] != '(' && sa[i] != ')' && sa[i] != '+' && sa[i] != '-') {
                    sb.append(sa[i]);
                    i++;
                }
                vals.push(Long.parseLong(sb.toString()));
                continue;
            }
            for (;;) {
                if (ops.isEmpty() || sa[i] == '(' || prec(sa[i]) > prec(ops.peek())) {
                    ops.push(sa[i]);
                    break;
                }
                char c = ops.pop();
                if (c == '(') {
                    break;
                }
                long v2 = vals.pop(), v1 = vals.pop();
                vals.push(calc(c, v1, v2));
            }
            i++;
        }
        while (!ops.isEmpty()) {
            char c = ops.pop();
            long v2 = vals.pop();
            if (vals.isEmpty()) {
                return (int) v2;
            }
            long v1 = vals.pop();
            vals.push(calc(c, v1, v2));
        }
        long ans = vals.pop();
        return (int) ans;
    }
}
