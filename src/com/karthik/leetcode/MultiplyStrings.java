/*
 * LeetCode problem : https://leetcode.com/problems/multiply-strings/
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if ((num1 == null || num1.isEmpty()) && (num2 == null || num2.isEmpty())) {
            return "0";
        }
        if (num1 == null || num1.isEmpty()) {
            return num2;
        }
        if (num2 == null || num2.isEmpty()) {
            return num1;
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[][] product = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                product[i][j] = i * j;
            }
        }

        int n = num1.length(), m = num2.length();
        boolean swap = false;
        if (n < m) {
            swap = true;
            int tmp = n;
            n = m;
            m = tmp;
        }

        char[] a = swap ? num2.toCharArray() : num1.toCharArray(), b = swap ? num1.toCharArray() : num2.toCharArray();
        int[] ans = new int[n + m];
        for (int j = m - 1; j >= 0; j--) {
            int y = b[j] - '0';
            for (int i = n - 1; i >= 0; i--) {
                int x = a[i] - '0';
                int prod = x > y ? product[y][x] : product[x][y];
                int sum = prod + ans[i + j + 1];

                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n + m; i++) {
            if (ans[i] == 0 && sb.length() == 0) {
                continue;
            }
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String... args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply("93750", "0"));
    }
}
