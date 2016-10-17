/*
 * Leetcode: https://leetcode.com/problems/jump-game-ii/
 *
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int fuel = nums[0], dist = nums[0], jump = 1, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (i == n - 1) {
                return jump;
            }
            if (i + nums[i] > dist) {
                dist = i + nums[i];
            }
            fuel--;
            if (fuel == 0) {
                jump++;
                fuel = dist - i;
            }
        }
        return jump;
    }

    public static void main(String... args) {
        JumpGame2 jg = new JumpGame2();
        int nums[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0};
        System.out.println(jg.jump(nums));
    }
}
