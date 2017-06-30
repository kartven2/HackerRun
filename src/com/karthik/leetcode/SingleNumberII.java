/*
 * LeetCode Problem: https://leetcode.com/problems/single-number-ii/#/description
 *
 * Given an array of integers, every element appears three times except for one,
 * which appears exactly once. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SingleNumberII {

    public int singleNumber(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (((a[j] >> i) & 1) == 1) {
                    cnt++;
                }
            }
            cnt %= 3;
            if (cnt > 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
