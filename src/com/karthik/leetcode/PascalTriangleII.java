/*
 * Leetcode: https://leetcode.com/problems/pascals-triangle-ii/#/description
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Could you optimize your algorithm to use only O(k) extra space?
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PascalTriangleII {

    public List<Integer> getRow(int n) {
        List<Integer> result = new LinkedList<>();
        if (n < 0) {
            return result;
        }
        int[] arr = new int[n + 1];
        arr[0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0 || j == i) {
                    arr[j] = 1;
                } else {
                    arr[j] = arr[j] + arr[j - 1];
                }
            }
        }
        for (int x : arr) {
            result.add(x);
        }
        return result;
    }
}
