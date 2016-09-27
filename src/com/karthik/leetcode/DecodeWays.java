/*
 * https://leetcode.com/problems/decode-ways/
 *
 * Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * Given encoded message "12",
 * it could be decoded as "AB" (1 2) or "L" (12).
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int x = Integer.parseInt(s.substring(i - 1, i));
            int y = Integer.parseInt(s.substring(i - 2, i));
            if (x > 0 && x < 10) {
                dp[i] += dp[i - 1];
            }
            if (y > 9 && y < 27) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String... args) {
        DecodeWays dw = new DecodeWays();
        System.out.println(dw.numDecodings("10"));
    }
}
