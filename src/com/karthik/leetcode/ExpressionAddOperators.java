/*
 * LeetCode: https://leetcode.com/problems/expression-add-operators/
 *
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or *
 * between the digits so they evaluate to the target value.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ExpressionAddOperators {

    private long applyOp(char op, long op2, long op1) {
        switch (op) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            default:
                throw new UnsupportedOperationException("Bad Expression");
        }
    }

    private boolean hasPrecedence(char op1, char op2) {
        return op1 == '*' || op2 != '*';
    }

    private Integer evalExp(String exp) {
        if (exp.isEmpty()) {
            return null;
        }
        Stack<Long> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int n = exp.length();
        char[] ex = exp.toCharArray();
        for (int i = 0; i < n;) {
            if (ex[i] >= '0' && ex[i] <= '9') {
                StringBuilder sb = new StringBuilder();
                int j = i;
                while (j < n && ex[j] >= '0' && ex[j] <= '9') {
                    sb.append(ex[j++]);
                }
                if (sb.length() > 1 && sb.charAt(0) == '0') {
                    return null;
                }
                i = j;
                vals.push(Long.parseLong(sb.toString()));
            } else {
                while (!ops.isEmpty() && hasPrecedence(ops.peek(), ex[i])) {
                    vals.push(applyOp(ops.peek(), vals.pop(), vals.pop()));
                    ops.pop();
                }
                ops.push(ex[i]);
                i++;
            }
        }
        while (!ops.isEmpty()) {
            vals.push(applyOp(ops.peek(), vals.pop(), vals.pop()));
            ops.pop();
        }
        long ans = vals.pop();
        return ans > Integer.MAX_VALUE ? null : (int) ans;
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        if (num == null || num.length() < 2) {
            if (num.length() == 1 && Integer.parseInt(num) == target) {
                result.add(num);
            }
            return result;
        }
        int n = num.length();
        for (String e : addToList(num, 0, n - 1)) {
            char lchar = e.charAt(e.length() - 1);
            if (lchar >= '0' && lchar <= '9') {
                Integer x = evalExp(e);
                if (x != null && x == target) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    private List<String> addToList(String num, int lo, int hi) {
        if (hi == lo) {
            List<String> res = new LinkedList<>();
            res.add(num.charAt(hi) + "");
            res.add(num.charAt(hi) + "+");
            res.add(num.charAt(hi) + "-");
            res.add(num.charAt(hi) + "*");
            return res;
        }
        int mid = lo + hi >> 1;
        List<String> left = addToList(num, lo, mid);
        List<String> right = addToList(num, mid + 1, hi);
        List<String> merge = new LinkedList<>();
        for (String l : left) {
            for (String r : right) {
                merge.add(l + r);
            }
        }
        return merge;
    }

    public static void main(String... args) {
        ExpressionAddOperators eao = new ExpressionAddOperators();
        List<String> res = eao.addOperators("123456789", 45);
        for (String x : res) {
            System.out.println(x);
        }
    }
}
