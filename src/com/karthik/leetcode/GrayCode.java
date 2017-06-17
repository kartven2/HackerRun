/*
 * LeetCode: https://leetcode.com/problems/gray-code/#/description
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code,
 * print the sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GrayCode {
    public List<Integer> grayCode(int b) {
        List<Integer> result = new LinkedList<>();
        if (b < 0) {
            return result;
        }
        int n = b == 0 ? 1 : 1 << b;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        int k = 0, j = 2, l = 0;
        while (j <= n) {
            j <<= 1;
            k = 0;
            l++;
            int[] tmp = new int[j];
            for (int i = 0; i < dp.length; i++) {
                tmp[k++] = dp[i];
            }
            for (int i = dp.length - 1; i >= 0 && k < n; i--) {
                tmp[k++] = (dp[i] | (1 << l));
            }
            dp = tmp;
            tmp = null;
        }
        for (int i = 0; i < n; i++) {
            result.add(dp[i]);
        }
        return result;
    }
}
