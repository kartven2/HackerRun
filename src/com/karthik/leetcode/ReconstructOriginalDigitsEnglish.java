/*
 * LeetCode: https://leetcode.com/problems/reconstruct-original-digits-from-english/description/
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 *
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReconstructOriginalDigitsEnglish {

    public String originalDigits(String s) {
        if (s == null || s.trim().isEmpty()) {
            return s;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        char[][] c = {{'z', '0'}, {'w', '2'}, {'u', '4'}, {'x', '6'}, {'g', '8'}, {'f', '5'}, {'h', '3'}, {'s', '7'}, {'i', '9'}, {'e', '1'}};
        String[] en = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] ans = new int[10];
        for (int i = 0; i < c.length; i++) {
            if (cnt[c[i][0] - 'a'] > 0) {
                int sum = cnt[c[i][0] - 'a'];
                ans[c[i][1] - '0'] = sum;
                for (int j = 0; j < en[c[i][1] - '0'].length(); j++) {
                    cnt[en[c[i][1] - '0'].charAt(j) - 'a'] -= sum;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < ans[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
