/*
 * Leetcode: https://leetcode.com/problems/wiggle-subsequence/#/description
 *
 * A sequence of numbers is called a wiggle sequence if the differences between
 * successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3)
 * are alternately positive and negative.
 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences,
 * the first because its first two differences are positive and the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 *
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 *
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WiggleSubsequence {

    public int wiggleMaxLength2(int[] a) {
        if (a == null) {
            return 0;
        }
        if (a.length < 2) {
            return a.length;
        }
        int n = a.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] - a[i - 1] > 0) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (a[i] - a[i - 1] < 0) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[n - 1], down[n - 1]);
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }
        int n = nums.length;
        int[] a = new int[n - 1];
        for (int i = 1; i < n; i++) {
            a[i - 1] = nums[i] - nums[i - 1];
        }
        int sign = a[0] > 0 ? 0 : a[0] < 0 ? 1 : 2;
        int ans = a[0] == 0 ? 1 : 2;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != 0) {
                if (sign == 2) {
                    ans++;
                    sign = a[i] > 0 ? 0 : 1;
                } else if (a[i] > 0 && sign == 1 || a[i] < 0 && sign == 0) {
                    ans++;
                    sign = sign == 0 ? 1 : 0;
                }
            }
        }
        return ans;
    }
}
