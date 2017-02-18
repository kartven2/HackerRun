/*
 * LeetCode: https://leetcode.com/problems/basic-calculator-ii/
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BasicCalculatorII {

    private int priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                throw new IllegalArgumentException("Invalid Character Found");
        }
    }

    private String getValidExp(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                switch (c) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        if (sb.toString().isEmpty() || sb.charAt(sb.length() - 1) == '+'
                                || sb.charAt(sb.length() - 1) == '-' || sb.charAt(sb.length() - 1) == '*'
                                || sb.charAt(sb.length() - 1) == '/') {
                            sb.append('0');
                        }
                        sb.append(c);
                        break;
                    case '0':
                        if (!(sb.toString().isEmpty() || sb.charAt(sb.length() - 1) == '+'
                                || sb.charAt(sb.length() - 1) == '-' || sb.charAt(sb.length() - 1) == '*'
                                || sb.charAt(sb.length() - 1) == '/')) {
                            sb.append(c);
                        }
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }
        if (sb.toString().isEmpty()) {
            return "0";
        }
        if (sb.charAt(sb.length() - 1) == '+' || sb.charAt(sb.length() - 1) == '-' || sb.charAt(sb.length() - 1) == '*' || sb.charAt(sb.length() - 1) == '/') {
            sb.append("0");
        }
        return sb.toString();
    }

    public int calculate(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        String exp = getValidExp(s);
        Stack<Character> ops = new Stack<>();
        Stack<Long> vals = new Stack<>();
        int n = exp.length();
        for (int i = 0; i < n;) {
            char c = exp.charAt(i);
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (ops.isEmpty() || priority(ops.peek()) < priority(c)) {
                        ops.push(c);
                    } else if (priority(ops.peek()) >= priority(c)) {
                        while (!ops.isEmpty() && priority(ops.peek()) >= priority(c)) {
                            char op = ops.pop();
                            long val2 = vals.pop();
                            long val1 = vals.pop();
                            switch (op) {
                                case '+':
                                    vals.push(val1 + val2);
                                    break;
                                case '-':
                                    vals.push(val1 - val2);
                                    break;
                                case '*':
                                    vals.push(val1 * val2);
                                    break;
                                case '/':
                                    vals.push(val1 / val2);
                                    break;
                            }
                        }
                        ops.push(c);
                    }
                    i++;
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    while (i < n && exp.charAt(i) != '+' && exp.charAt(i) != '-' && exp.charAt(i) != '*' && exp.charAt(i) != '/') {
                        sb.append(exp.charAt(i));
                        i++;
                    }
                    vals.push(Long.parseLong(sb.toString()));
            }
        }
        while (!ops.isEmpty()) {
            char op = ops.pop();
            long val2 = vals.pop();
            long val1 = vals.pop();
            switch (op) {
                case '+':
                    vals.push(val1 + val2);
                    break;
                case '-':
                    vals.push(val1 - val2);
                    break;
                case '*':
                    vals.push(val1 * val2);
                    break;
                case '/':
                    vals.push(val1 / val2);
                    break;
            }
        }
        return (int) vals.pop().longValue();
    }
}
