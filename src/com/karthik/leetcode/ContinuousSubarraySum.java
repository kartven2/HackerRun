/*
 * LeetCode: https://leetcode.com/problems/continuous-subarray-sum/#/description
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous subarray of size
 * at least 2 that sums up to the multiple of k,
 * that is, sums up to n*k where n is also an integer.
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is
 * in the range of a signed 32-bit integer.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return k == 0;
        }
        int n = nums.length;
        if (k == 0) {
            for (int i = 1; i < n; i++) {
                if (nums[i] == 0 && nums[i] == nums[i - 1]) {
                    return true;
                }
            }
            return false;
        }
        k = k < 0 ? -k : k;
        int lastVal = 0, currVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    lastVal = nums[j];
                } else {
                    currVal = lastVal + nums[j];
                    if (currVal % k == 0) {
                        return true;
                    }
                    lastVal = currVal;
                }
            }
        }
        return false;
    }

    public static void main(String... args) {
        ContinuousSubarraySum cc = new ContinuousSubarraySum();
        int input[] = {23, 2, 4, 6, 7};
        System.out.println(cc.checkSubarraySum(input, 6));
    }
}
