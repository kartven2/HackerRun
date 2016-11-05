/*
 * LeetCode: https://leetcode.com/problems/interleaving-string/
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class InterleavingString {

    public static void main(String... args) {
        InterleavingString is = new InterleavingString();
        System.out.println(is.interleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));

        System.out.println(is.interleave("a", "b", "ab"));

    }

    public boolean interleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null) {
            return s3 == null;
        }

        if (s1 == null || s1.isEmpty()) {
            return s3.equals(s2);
        }

        if (s2 == null || s2.isEmpty()) {
            return s3.equals(s1);
        }

        int n = s1.length(), m = s2.length(), p = s3.length();
        if (p != n + m) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0 && s3.charAt(j - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0 && s3.charAt(i - 1) == s1.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i > 0 && j > 0 && s3.charAt(i + j - 1) != s2.charAt(j - 1) && s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i > 0 && j > 0 && s3.charAt(i + j - 1) != s1.charAt(i - 1) && s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                } else if (i > 0 && j > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null) {
            return s3 == null;
        }

        if (s1 == null || s1.isEmpty()) {
            return s3.equals(s2);
        }

        if (s2 == null || s2.isEmpty()) {
            return s3.equals(s1);
        }

        int i = 0, j = 0, k = 0, in = s1.length(), jn = s2.length(), kn = s3.length();

        if (kn != (in + jn)) {
            return false;
        }

        return isInterleave(s1, s2, s3, i, j, k, in, jn, kn);
    }

    private boolean isInterleave(String s1, String s2, String s3, int i, int j, int k, int in, int jn, int kn) {
        if (k >= kn) {
            return true;
        }
        if (i >= in) {
            return s3.substring(k).equals(s2.substring(j));
        }
        if (j >= jn) {
            return s3.substring(k).equals(s1.substring(i));
        }
        if (s3.charAt(k) != s2.charAt(j) && s3.charAt(k) != s1.charAt(i)) {
            return false;
        }
        if (s2.charAt(j) == s1.charAt(i)) {
            return isInterleave(s1, s2, s3, i + 1, j, k + 1, in, jn, kn) || isInterleave(s1, s2, s3, i, j + 1, k + 1, in, jn, kn);
        }
        if (s1.charAt(i) == s3.charAt(k)) {
            return isInterleave(s1, s2, s3, i + 1, j, k + 1, in, jn, kn);
        } else {
            return isInterleave(s1, s2, s3, i, j + 1, k + 1, in, jn, kn);
        }
    }
}
