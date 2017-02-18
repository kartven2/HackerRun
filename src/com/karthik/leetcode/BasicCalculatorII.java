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

import java.util.Arrays;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        List<Character> sign = Arrays.asList(new Character[]{'+', '-', '*', '/'});
        int result = 0, curResult = 0, n = s.length();
        char op = '+';
        for (int i = 0; i < n;) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (sign.contains(c)) {
                if (c == '+' || c == '-') {
                    result += curResult;
                    curResult = 0;
                }
                op = c;
                i++;
                continue;
            }
            int tmp = c - '0', num = 0;
            while (++i < n && !sign.contains(s.charAt(i))) {
                c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                num = c - '0';
                tmp = tmp * 10 + num;
            }
            switch (op) {
                case '+':
                    curResult += tmp;
                    break;
                case '-':
                    curResult -= tmp;
                    break;
                case '*':
                    curResult *= tmp;
                    break;
                case '/':
                    curResult /= tmp;
                    break;
                default:
                    throw new IllegalArgumentException("Bad operator");
            }
        }
        return result + curResult;
    }
}
