/*
 * Leetcode: https://leetcode.com/problems/3sum/
 *
 * Given an array S of n integers, are there elements a, b, c
 * in S such that a + b + c = 0? Find all unique triplets in the
 * array which gives the sum of zero.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ThreeSum {

    private final Map<String, List<Integer>> result = new HashMap<>();

    private boolean binarySearch(int[] nums, int lo, int hi, int value) {
        if (lo > hi) {
            return false;
        }
        int mid = (lo + hi) >> 1;
        if (nums[mid] == value) {
            return true;
        }
        if (nums[mid] < value) {
            return binarySearch(nums, mid + 1, hi, value);
        } else {
            return binarySearch(nums, lo, mid - 1, value);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>(result.values());
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (binarySearch(nums, j + 1, n - 1, -(nums[i] + nums[j]))) {
                    StringBuilder sb = new StringBuilder();
                    int a = nums[i], b = nums[j], c = -(nums[i] + nums[j]);
                    List<Integer> vals = new ArrayList<>();
                    sb.append(a);
                    sb.append(b);
                    sb.append(c);
                    vals.add(a);
                    vals.add(b);
                    vals.add(c);
                    result.put(sb.toString(), vals);
                }
            }
        }
        return new ArrayList<>(result.values());
    }
}
