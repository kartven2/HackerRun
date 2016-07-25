/*
 * LeetCode problem :
 * https://leetcode.com/problems/dungeon-game/
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DungeonGame {

    private static final int MAX = Integer.MAX_VALUE;
    private int[][] dungeon;
    private int n;
    private int m;
    private int[][] dp;

    public int calculateMinimumHP(int[][] dungeon) {
        this.dungeon = dungeon;
        this.n = dungeon.length;
        if (n == 0) {
            return 0;
        }
        this.m = dungeon[0].length;
        this.dp = new int[n + 1][m + 1];
        for (int k = 0; k <= m; k++) {
            dp[n][k] = MAX;
        }
        for (int k = 0; k <= n; k++) {
            dp[k][m] = MAX;
        }
        dp[n - 1][m] = dp[n][m - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }

    public static void main(String... args) {
        DungeonGame dg = new DungeonGame();
        //int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        //int[][] dungeon = {{100}};
        //int[][] dungeon = {{0, -3}};
        int[][] dungeon = {{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
        System.out.print(dg.calculateMinimumHP(dungeon));
    }
}
