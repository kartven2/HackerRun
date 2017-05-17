package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RollADice {

    public static void main(String... args) {
        RollADice ra = new RollADice();
        for (int i = 0; i <= 10; i++) {
            System.out.println("For " + i + "---" + ra.rollADice(i));
        }
        System.out.println("For " + 610 + "---" + ra.rollADice(610));
        System.out.println("----end-----");
        for (int i = 0; i <= 10; i++) {
            System.out.println("For " + i + "---" + ra.rollADice2(i));
        }
        System.out.println("For " + 610 + "---" + ra.rollADice2(610));
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

    private int rollADice2(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i <= 6) {
                dp[i] = 1;
            }
            for (int j = i - 1; j > 0 && j >= i - 6; j--) {
                dp[i] += dp[j];
            }
        }
        return dp[n];
    }
}
