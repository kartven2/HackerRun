/*
 * LeetCode Problem: https://leetcode.com/problems/perfect-squares/#/description 
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PerfectSquares {

    public static void main(String... args) {
        PerfectSquares ps = new PerfectSquares();
        ps.numSquares(43);
    }

    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        List<Integer> list = new LinkedList<>();
        int a = 1, sq = 0;
        while (a * a <= n) {
            sq = a * a;
            list.add(sq);
            dp[sq] = 1;
            a++;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 0) {
                int min = i;
                for (int x : list) {
                    if (x > i) {
                        break;
                    }
                    if (1 + dp[i - x] < min) {
                        min = 1 + dp[i - x];
                    }
                }
                dp[i] = min;
            }
        }
        return dp[n];
    }
}
