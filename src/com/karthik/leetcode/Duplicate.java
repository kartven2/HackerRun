/*
 * LeetCode problem :
 * https://leetcode.com/problems/contains-duplicate/
 * Given an array of integers, find if the array contains
 * any duplicates. Your function should return true if any
 * value appears at least twice in the array, and it should
 * return false if every element is distinct.
 */
package com.karthik.leetcode;

import java.util.BitSet;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Duplicate {

    public boolean containsDuplicate(int[] nums) {
        BitSet bspos = new BitSet((int) 1e6);
        BitSet bsneg = new BitSet((int) 1e6);
        BitSet bs = bspos;
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            bs = nums[i] < 0 ? bsneg : bspos;
            value = nums[i] < 0 ? -nums[i] : nums[i];
            if (bs.get(value)) {
                return true;
            }
            bs.set(value);
        }
        return false;
    }
}
