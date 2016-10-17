/*
 * Leetcode: https://leetcode.com/problems/jump-game/
 *
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int fuel = 1, dist = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            fuel--;
            if (i + nums[i] > dist) {
                dist = i + nums[i];
                fuel = nums[i];
            }
            if (fuel == 0 && i < n - 1) {
                return false;
            }
        }
        return true;
    }
}
