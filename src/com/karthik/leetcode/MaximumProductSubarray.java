/*
 * LeetCode: https://leetcode.com/problems/maximum-product-subarray/
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0], max = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i], nums[i] * max);
                min = Math.min(nums[i], nums[i] * min);
            } else {
                int tmp = max;
                max = Math.max(nums[i], nums[i] * min);
                min = Math.min(nums[i], nums[i] * tmp);
            }
            result = Math.max(result, max);
        }
        return result;
    }
}
