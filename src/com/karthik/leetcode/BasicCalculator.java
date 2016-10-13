/*
 * LeetCode: https://leetcode.com/problems/basic-calculator/
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BasicCalculator {

    public static void main(String... args) {
        BasicCalculator bc = new BasicCalculator();
        System.out.print(bc.calculate("(3+2)"));
    }

    private long compute(long v1, long v2, char op) {
        switch (op) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            default:
                throw new IllegalArgumentException("Bad Input");
        }
    }

    private static final Map<Character, Integer> pc = new HashMap<>();

    static {
        pc.put('(', 0);
        pc.put(')', 0);
        pc.put('+', 1);
        pc.put('-', 1);
    }

    private boolean isNumber(char c) {
        return c != '(' && c != ')' && c != '+' && c != '-' && c != ' ';
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Long> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int n = s.length();
        char[] sa = s.toCharArray();
        for (int i = 0; i < n;) {
            char c = sa[i];
            if (c == ' ') {
                i++;
                continue;
            }
            if (isNumber(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < n && isNumber(sa[i])) {
                    sb.append(sa[i]);
                    i++;
                }
                vals.push(Long.parseLong(sb.toString()));
                continue;
            }
            for (;;) {
                if (ops.isEmpty() || c == '(' || pc.get(c) > pc.get(ops.peek())) {
                    ops.push(c);
                    break;
                }

                char op = ops.pop();
                if (op == '(') {
                    break;
                }

                long v2 = vals.pop(), v1 = vals.pop();
                vals.push(compute(v1, v2, op));
            }
            i++;
        }

        while (!ops.isEmpty()) {
            char op = ops.pop();
            long v2 = vals.pop();
            if (vals.isEmpty()) {
                return (int) v2;
            }
            long v1 = vals.pop();
            vals.push(compute(v1, v2, op));
        }

        long ans = vals.pop();
        return (int) ans;
    }
}
