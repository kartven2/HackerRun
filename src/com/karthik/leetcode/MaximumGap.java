/*
 * LeetCode problem :
 * https://leetcode.com/problems/maximum-gap/
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximumGap {

    public static void main(String... args) {
        MaximumGap mg = new MaximumGap();
        int[] x = {15252, 16764, 27963, 7817, 26155,
            20757, 3478, 22602, 20404, 6739, 16790, 10588, 16521, 6644,
            20880, 15632, 27078, 25463, 20124, 15728, 30042, 16604, 17223,
            4388, 23646, 32683, 23688, 12439, 30630, 3895, 7926, 22101, 32406,
            21540, 31799, 3768, 26679, 21799, 23740};
        System.out.println(mg.maximumGap(x));
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = nums[0], max = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        if (min == max || n == 2) {
            return max - min;
        }
        Integer[] bmax = new Integer[n - 1];
        Integer[] bmin = new Integer[n - 1];
        float delta = (float) (max - min) / ((float) n - 1);
        for (int i = 0; i < n; i++) {
            if (nums[i] != min && nums[i] != max) {
                int bidx = (int) Math.floor((nums[i] - min) / delta);
                bmax[bidx] = bmax[bidx] == null ? nums[i] : Math.max(bmax[bidx], nums[i]);
                bmin[bidx] = bmin[bidx] == null ? nums[i] : Math.min(bmin[bidx], nums[i]);
            }
        }
        int prev = min;
        Integer diff = null;
        for (int i = 0; i < n - 1; i++) {
            if (bmin[i] != null) {
                diff = diff == null ? (bmin[i] - prev) : Math.max(diff, bmin[i] - prev);
                prev = bmax[i];
            }
        }
        return diff == null ? max - prev : Math.max(diff, max - prev);
    }
}
