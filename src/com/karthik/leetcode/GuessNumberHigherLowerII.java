/*
 * LeetCode: https://leetcode.com/problems/guess-number-higher-or-lower-ii/#/description
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However, when you guess a particular number x, and you guess wrong, you pay $x.
 * You win the game when you guess the number I picked.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GuessNumberHigherLowerII {

    public static void main(String... args) {
        GuessNumberHigherLowerII gh = new GuessNumberHigherLowerII();
        System.out.println(gh.getMoneyAmount(10));
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int[] a : dp) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        int result = build(1, n, dp);
        return result;
    }

    private int build(int lo, int hi, int[][] dp) {
        if (lo >= hi) {
            return 0;
        }
        if(dp[lo][hi] != Integer.MAX_VALUE) {
            return dp[lo][hi];
        }
        for (int i = lo; i <= hi; i++) {
             dp[lo][hi] =  Math.min(dp[lo][hi], Math.max(i+build(lo, i-1, dp), i+build(i+1, hi, dp)));
        }
        return dp[lo][hi];
    }
}
