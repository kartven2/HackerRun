/*
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array, find the first missing positive integer.
 * Your algorithm should run in O(n) time and uses constant space.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        int j = 0, k = n;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                nums[i] = ++k;
            }
            if (nums[i] < 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        for (int i = 0; i < j; i++) {
            nums[i] *= -1;
        }
        for (int i = j; i < n; i++) {
            int value = Math.abs(nums[i]) - 1;
            if (value <= n - 1 && nums[value] > 0) {
                nums[value] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String... args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        int[] x = {1000, -1};
        System.out.println(fmp.firstMissingPositive(x));
    }
}
