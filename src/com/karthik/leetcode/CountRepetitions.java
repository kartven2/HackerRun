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

    public static void main(String... args) {
        CountRepetitions cr = new CountRepetitions();
        cr.getMaxRepetitions("phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenzkycxf", 100, "xtlsgypsfa", 1);
    }

}
