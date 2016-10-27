/*
 * LeetCode: https://leetcode.com/problems/largest-number/
 *
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LargestNumber {

    private int compare(int a, int b) {
        return (a + "" + b).compareTo(b + "" + a);
    }

    private void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int i = lo + 1, lt = lo, gt = hi;
        int v = nums[lo];
        while (i <= gt) {
            if (compare(v, nums[i]) < 0) {
                exchange(nums, i++, lt++);
            } else if (compare(v, nums[i]) > 0) {
                exchange(nums, i, gt--);
            } else {
                i++;
            }
        }
        sort(nums, lo, lt - 1);
        sort(nums, lt + 1, hi);
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i == nums.length - 1) {
                return "0";
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            exchange(nums, i, rand);
        }
        sort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }
}
