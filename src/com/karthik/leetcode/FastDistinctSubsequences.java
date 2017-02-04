/*
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Given a string S and a string T,
 * count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed
 * from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ACE" is a subsequence of
 * "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastDistinctSubsequences {
    public int numDistinct(String s, String t) {
        if (s == null || t == null || t.length() > s.length()) {
            return 0;
        }
        int tl = t.length(), sl = s.length();
        int[][] dp = new int[tl+1][sl+1];
        for(int i=0; i<=sl; i++) {
            dp[0][i] = 1;
        }
        for(int i=1; i<=tl; i++) {
            char ct = t.charAt(i-1);
            for(int j=1; j<=sl; j++) {
                char st = s.charAt(j-1);
                if(ct!=st) {
                    dp[i][j] = dp[i][j-1];
                    continue;
                }
                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
            }
        }
        return dp[tl][sl];
    }
    
    public static void main(String...args) {
        FastDistinctSubsequences fds = new FastDistinctSubsequences();
        System.out.print(fds.numDistinct("rabbbit", "rabbit"));
    }
}
