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

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastExpressionAddOperators {

    private static String a = null;
    private static int tgt = 0;
    private static List<String> result = null;
    private static int n = 0;

    public List<String> addOperators(String num, int target) {
        result = new LinkedList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        a = num;
        tgt = target;
        n = a.length();
        collect("", 0, 0, 0);
        return result;
    }

    private void collect(String exp, int startPos, long value, long multiplier) {
        if (startPos == n) {
            if (value == tgt) {
                result.add(exp);
            }
            return;
        }
        for (int i = startPos; i < n; i++) {
            if (i > startPos && a.charAt(startPos) == '0') {
                break;
            }
            long cur = Long.parseLong(a.substring(startPos, i + 1));
            if (startPos == 0) {
                collect(exp + cur, i + 1, cur, cur);
            } else {
                collect(exp + "+" + cur, i + 1, value + cur, cur);
                collect(exp + "-" + cur, i + 1, value - cur, -cur);
                collect(exp + "*" + cur, i + 1, value - multiplier + cur * multiplier, cur * multiplier);
            }
        }
    }

    public static void main(String... args) {
        FastExpressionAddOperators fea = new FastExpressionAddOperators();
        fea.addOperators("", 5);
        for (String x : result) {
            System.out.println(x);
        }
    }
}
