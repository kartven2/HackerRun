/*
 * Leetcode: https://leetcode.com/problems/split-array-largest-sum/
 *
 * Given an array which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SplitArrayLargestSum {

    public static void main(String... args) {
        SplitArrayLargestSum sals = new SplitArrayLargestSum();
        int[] nums = {7, 2, 5, 10, 8};
        System.out.println(sals.splitArray(nums, 2));
    }

    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (m > n) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        if (m == n) {
            return max;
        }
        if (m == 1) {
            return (int) sum;
        }
        return (int) binarySearch(nums, m, max, sum);
    }

    private long binarySearch(int[] nums, int m, long lo, long hi) {
        long mid = 0;
        while (lo < hi) {
            mid = (lo + hi) >> 1;
            if (isValid(nums, m, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    private boolean isValid(int[] nums, int m, long s) {
        long sum = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= s) {
                sum += nums[i];
            } else {
                sum = nums[i];
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
