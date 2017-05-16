/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karthik.leetcode;

/**
 *
 * @author kafy8
 */
public class RollADice {

    public static void main(String... args) {
        RollADice ra = new RollADice();
         //System.out.println("For " + 610 + "---" + ra.rollADice(610));
        for (int i = 0; i <= 10; i++) {
            System.out.println("For " + i + "---" + ra.rollADice(i));
        }
    }

    private int rollADice(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; (i - j) >= 0 && j <= 6; j++) {
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }
}
