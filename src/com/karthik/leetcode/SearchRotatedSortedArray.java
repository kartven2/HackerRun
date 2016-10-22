/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int lo, int hi, int target) {
        if (lo > hi) {
            return -1;
        }
        int mid = (lo + hi) >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[lo] <= nums[mid]) {
            if (target >= nums[lo] && target < nums[mid]) {
                return search(nums, lo, mid - 1, target);
            } else {
                return search(nums, mid + 1, hi, target);
            }
        } else if (target <= nums[hi] && target > nums[mid]) {
            return search(nums, mid + 1, hi, target);
        } else {
            return search(nums, lo, mid - 1, target);
        }
    }

    public static void main(String... args) {
        SearchRotatedSortedArray srsa = new SearchRotatedSortedArray();
        int[] nums = {3, 4, 5, 6, 1, 2};
        System.out.println(srsa.search(nums, 2));
    }
}
