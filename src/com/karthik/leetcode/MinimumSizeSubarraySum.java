/*
 * LeetCode Problem: https://leetcode.com/problems/minimum-size-subarray-sum/#/description
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int n = num.length, l = 0, r = 0, sum = 0, min = Integer.MAX_VALUE;
        while (r < n) {
            while (r < n && sum < s) {
                sum += num[r++];
            }
            while (l < r && sum >= s) {
                sum -= num[l++];
            }
            if (l > 0 && sum + num[l - 1] >= s) {
                min = Math.min(min, r - (l - 1));
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, l = 0, r = 0, sum = 0, min = Integer.MAX_VALUE;
        while (r < n) {
            sum += nums[r++];
            while (l < r && sum >= s) {
                min = Math.min(min, r - l);
                sum -= nums[l++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
