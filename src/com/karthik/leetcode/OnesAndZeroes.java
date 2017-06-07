/*
 * Leetcode: https://leetcode.com/problems/ones-and-zeroes/#/description
 *
 * In the computer world, use restricted resource you have to generate
 * maximum benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively.
 * On the other hand, there is an array with strings consisting of only 0s and 1s.
 * Now your task is to find the maximum number of strings that
 * you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class OnesAndZeroes {

    public int findMaxForm2(String[] a, int z, int o) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int[][] dp = new int[z + 1][o + 1];
        for (String s : a) {
            int[] count = count(s);
            for (int i = z; i >= count[0]; i--) {
                for (int j = o; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
                }
            }
        }
        return dp[z][o];
    }

    private int[] count(String s) {
        int[] count = new int[2];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }
        return count;
    }

    public int findMaxForm(String[] a, int z, int o) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Arrays.sort(a);
        int n = a.length;
        Integer[][][] dp = new Integer[n][z + 1][o + 1];
        return findMax(a, dp, z, o, 0);
    }

    class Balance {

        private int o;
        private int z;
        private boolean possible = true;
    }

    private Balance process(String a, int z, int o) {
        Balance balance = new Balance();
        balance.z = z;
        balance.o = o;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0') {
                balance.z--;
            } else {
                balance.o--;
            }
        }
        balance.possible = balance.z >= 0 && balance.o >= 0;
        return balance;
    }

    private int findMax(String[] a, Integer[][][] dp, int z, int o, int start) {
        if (start == a.length || (z == 0 && o == 0)) {
            return 0;
        }
        if (dp[start][z][o] != null) {
            return dp[start][z][o];
        }
        Balance balance = process(a[start], z, o);
        dp[start][z][o] = balance.possible ? 1 + findMax(a, dp, balance.z, balance.o, start + 1) : 0;
        dp[start][z][o] = Math.max(dp[start][z][o], findMax(a, dp, z, o, start + 1));
        return dp[start][z][o];
    }

    public static void main(String... args) {
        OnesAndZeroes oz = new OnesAndZeroes();
        System.out.println(oz.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
}
