/*
 * Leetcode: https://leetcode.com/problems/lexicographical-numbers/
 *
 * Given an integer n, return 1 - n in lexicographical order.
 * Optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LexicographicalNumbers {

    private List<Integer> result = new ArrayList<>();
    private int n;

    public List<Integer> lexicalOrder(int n) {
        this.n = n;
        add(1);
        return result;
    }

    private void add(int m) {
        if (m > n) {
            return;
        }
        result.add(m);
        add(m * 10);
        if (m % 10 < 9) {
            add(m + 1);
        }
    }

    public static void main(String... args) {
        LexicographicalNumbers lm = new LexicographicalNumbers();
        List<Integer> x = lm.lexicalOrder(110);
        for (int y : x) {
            System.out.println(y);
        }
    }
}
