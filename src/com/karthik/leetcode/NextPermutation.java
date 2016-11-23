/*
 * Leetcode: https://leetcode.com/problems/next-permutation/
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 1,1,5 → 1,5,1
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NextPermutation {

    private void sort(int[] nums, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && nums[j - 1] > nums[j]; j--) {
                exchange(nums, j - 1, j);
            }
        }
    }

    private void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length, i = n - 2, j = n - 1;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i < 0) {
            i = 0;
            while (i < j) {
                exchange(nums, i, j);
                i++;
                j--;
            }
            return;
        }
        j = n - 1;
        while (j > i && nums[j] <= nums[i]) {
            j--;
        }
        exchange(nums, i, j);
        sort(nums, i + 1, n - 1);
    }
}
