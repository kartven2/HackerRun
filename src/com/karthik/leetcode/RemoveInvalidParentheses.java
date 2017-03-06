/*
 * Leetcode: https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Remove the minimum number of invalid parentheses in order to make
 * the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveInvalidParentheses {

    private boolean isValid(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        int count = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        if (s.trim().length() == 0) {
            result.add(s);
            return result;
        }
        Set<String> v = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        boolean found = false;
        while (!q.isEmpty()) {
            String x = q.remove();
            if (isValid(x)) {
                result.add(x);
                found = true;
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < x.length(); i++) {
                if (x.charAt(i) != '(' && x.charAt(i) != ')') {
                    continue;
                }
                String m = new StringBuilder().append(x.substring(0, i)).append(x.substring(i + 1)).toString();
                if (!v.contains(m)) {
                    q.add(m);
                    v.add(m);
                }
            }
        }
        return result;
    }
}
