/*
 * LeetCode: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        int[] stack = new int[tokens.length];
        int sp = 0, n = tokens.length;
        for (int i = 0; i < n; i++) {
            String s = tokens[i];
            int val = 0;
            if (s.equals("+")) {
                stack[sp - 2] = stack[sp - 2] + stack[sp - 1];
                sp--;
            } else if (s.equals("-")) {
                stack[sp - 2] = stack[sp - 2] - stack[sp - 1];
                sp--;
            } else if (s.equals("*")) {
                stack[sp - 2] = stack[sp - 2] * stack[sp - 1];
                sp--;
            } else if (s.equals("/")) {
                stack[sp - 2] = stack[sp - 2] / stack[sp - 1];
                sp--;
            } else {
                stack[sp++] = Integer.parseInt(s);
            }
        }
        return stack[sp - 1];
    }
}
