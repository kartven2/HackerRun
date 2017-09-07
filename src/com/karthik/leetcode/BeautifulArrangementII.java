/*
 * LeetCode: https://leetcode.com/problems/beautiful-arrangement-ii/description/
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BeautifulArrangementII {

    public int[] constructArray(int n, int k) {
        if (n <= 0 || k <= 0 || k > n - 1) {
            return null;
        }
        int[] ans = new int[n];
        int l = 1, r = k + 1, i = 0;
        while (l <= r) {
            ans[i++] = l++;
            if (l <= r) {
                ans[i++] = r--;
            }
        }
        for (int j = k + 2; j <= n; j++) {
            ans[i++] = j;
        }
        return ans;
    }
}
