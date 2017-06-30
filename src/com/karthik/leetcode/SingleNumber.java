/*
 * LeetCode Problem: https://leetcode.com/problems/single-number/#/description
 * Given an array of integers, every element appears twice except for one.
 * Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
*/
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        return xor;
    }
}