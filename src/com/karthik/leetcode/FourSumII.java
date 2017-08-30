/*
 * LeetCode: https://leetcode.com/problems/4sum-ii/description/
 * Given four lists A, B, C, D of integer values, compute how many
 * tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
 * All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FourSumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || C == null || C.length == 0 || D == null || D.length == 0) {
            return 0;
        }
        int k = A.length, l = B.length, m = C.length, n = D.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                Integer x = map.get(a + b);
                x = x == null ? 1 : x + 1;
                map.put((a + b), x);
            }
        }
        int ans = 0;
        for (int c : C) {
            for (int d : D) {
                Integer x = map.get(0 - c - d);
                if (x != null) {
                    ans += x;
                }
            }
        }
        return ans;
    }
}
