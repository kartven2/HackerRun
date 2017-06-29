/*
 * Leetcode: https://leetcode.com/problems/different-ways-to-add-parentheses/#/description
 *
 * Given a string of numbers and operators,
 * return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 */
package com.karthik.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DifferentWaysToAddParentheses {

    public static void main(String... args) {
        DifferentWaysToAddParentheses dwta = new DifferentWaysToAddParentheses();
        List<Integer> ans = dwta.diffWaysToCompute("5-2*4+3");
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new LinkedList<>();
        if (input == null || input.trim().length() == 0) {
            return result;
        }
        int n = input.length();
        List<Object> list = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                list.add(num);
                num = 0;
                list.add(c);
            }
        }
        list.add(num);
        Object[] obj = new Object[list.size()];
        List<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < obj.length; i++) {
            obj[i] = list.get(i);
            if (obj[i] instanceof Character) {
                indexes.add(i);
            }
        }
        return dfs(indexes, obj, 0, obj.length - 1);
    }

    private List<Integer> dfs(List<Integer> indexes, Object[] obj, int lo, int hi) {
        List<Integer> res = new LinkedList<>();
        if (lo > hi) {
            return res;
        }
        if (lo == hi) {
            int x = (Integer) obj[lo];
            res.add(x);
            return res;
        }
        for (int idx : indexes) {
            if (idx > lo && idx < hi) {
                List<Integer> left = dfs(indexes, obj, lo, idx - 1);
                List<Integer> right = dfs(indexes, obj, idx + 1, hi);
                for (int l : left) {
                    for (int r : right) {
                        char c = (Character) obj[idx];
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
