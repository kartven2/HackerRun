/*
 * LeetCode Problem : https://leetcode.com/problems/generate-parentheses/#/description
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GenerateParentheses {

    public static void main(String... args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> result = gp.generateParenthesis(3);
        for (String x : result) {
            System.out.println(x);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if (n <= 0) {
            return result;
        }
        collect(result, n - 1, n, "(");
        return result;
    }

    private void collect(List<String> result, int l, int r, String s) {
        if (r < l) {
            return;
        }
        if (l == 0 && r == 0) {
            result.add(s);
            return;
        }
        if (l > 0) {
            collect(result, l - 1, r, s + '(');
        }
        if (r > 0) {
            collect(result, l, r - 1, s + ')');
        }
    }
}
