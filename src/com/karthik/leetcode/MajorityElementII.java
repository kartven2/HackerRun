/*
 * LeetCode: https://leetcode.com/problems/majority-element-ii/
 * 
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length, count1 = 0, count2 = 0, candidate1 = 0, candidate2 = 1, needed = n / 3;
        for (int i = 0; i < n; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            } else if (count1 == 0) {
                count1 = 1;
                candidate1 = nums[i];
            } else if (count2 == 0) {
                count2 = 1;
                candidate2 = nums[i];
            } else {
                count1--;
                count2--;
            }
        }
        int freqa = 0, freqb = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == candidate1) {
                freqa++;
            } else if (nums[i] == candidate2) {
                freqb++;
            }
        }
        if (freqa > needed) {
            result.add(candidate1);
        }
        if (freqb > needed) {
            result.add(candidate2);
        }
        return result;
    }
}
