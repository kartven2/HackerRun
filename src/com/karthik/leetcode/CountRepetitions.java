/*
 * LeetCode: https://leetcode.com/problems/count-the-repetitions/#/description
 *
 * Define S = [s,n] as the string S which consists of n connected strings s.
 * For example, ["abc", 3] ="abcabcabc".
 * On the other hand, we define that string s1 can be obtained from string s2
 * if we can remove some characters from s2 such that it becomes s1. For example,
 * “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.
 * You are given two non-empty strings s1 and s2 (each at most 100 characters long)
 * and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2,
 * where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CountRepetitions {

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1 == null || s2 == null
                || s1.length() == 0 || s2.length() == 0
                || n1 <= 0 || n2 <= 0) {
            return 0;
        }
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        int i = 0, j = 0, cnt1 = 0, ans = 0;
        while (cnt1 < n1) {
            if (a[i] == b[j]) {
                j++;
                if (j == s2.length()) {
                    j = 0;
                    ans++;
                }
            }
            i++;
            if (i == s1.length()) {
                i = 0;
                cnt1++;
            }
        }
        return ans / n2;
    }
    class Pair {

        private int used;
        private int ans;

        Pair(int used, int ans) {
            this.used = used;
            this.ans = ans;
        }
    }

    private void inc(int[] p, int n) {
        if (++p[0] == n) {
            p[0] = 0;
            p[1]++;
        }
    }

    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || n1 <= 0 || n2 <= 0) {
            return 0;
        }
        int n = s1.length(), m = s2.length();
        Pair[][] dp = new Pair[n][m];
        int[] p1 = new int[2];
        int[] p2 = new int[2];
        while (p1[1] < n1) {
            while (p1[1] < n1 && s1.charAt(p1[0]) != s2.charAt(p2[0])) {
                inc(p1, n);
            }
            if (dp[p1[0]][p2[0]] == null) {
                dp[p1[0]][p2[0]] = new Pair(p1[1], p2[1]);
            } else {
                int deltap1 = p1[1] - dp[p1[0]][p2[0]].used;
                int deltap2 = p2[1] - dp[p1[0]][p2[0]].ans;
                int k = (n1 - deltap1 - 1) / deltap1;
                p1[1] += k * deltap1;
                p2[1] += k * deltap2;
            }
            inc(p1, n);
            inc(p2, m);
        }
        return p2[1] / n2;
    }

    public static void main(String... args) {
        CountRepetitions cr = new CountRepetitions();
        //cr.getMaxRepetitions2("phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenzkycxf", 100, "xtlsgypsfa", 1);
        cr.getMaxRepetitions2("acb", 4, "ab", 2);
        //cr.getMaxRepetitions2( "lovelivenanjomusicforever", 4,"nanjo", 2);
    }
}
