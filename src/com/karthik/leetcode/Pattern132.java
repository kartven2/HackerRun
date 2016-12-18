/*
 * LeetCode: https://leetcode.com/problems/132-pattern/
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern
 * is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 * Design an algorithm that takes a list of n numbers as input and
 * checks whether there is a 132 pattern in the list.
 * Note: n will be less than 15,000.
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Pattern132 {

    class Pair {

        int min, max;

        Pair(int a, int b) {
            min = a;
            max = b;
        }
    }

    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        Stack<Pair> stk = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if (stk.isEmpty() || c < stk.peek().min) {
                stk.push(new Pair(c, c));
            } else if (stk.peek().min < c && c < stk.peek().max) {
                return true;
            } else if (c > stk.peek().max) {
                Pair t = stk.pop();
                t.max = c;
                if (stk.isEmpty()) {
                    stk.push(t);
                } else {
                    while (!stk.isEmpty() && t.max > stk.peek().min) {
                        if (t.max < stk.peek().max) {
                            return true;
                        }
                        stk.pop();
                    }
                    stk.push(t);
                }
            }
        }
        return false;
    }
}
