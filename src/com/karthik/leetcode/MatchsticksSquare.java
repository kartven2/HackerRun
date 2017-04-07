/*
 * LeetCode: https://leetcode.com/problems/matchsticks-to-square/#/description
 *
 * Remember the story of Little Match Girl? By now,
 * you know exactly what matchsticks the little match girl has,
 * please find out a way you can make one square by using up all those matchsticks.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * Your input will be several matchsticks the girl has,
 * represented with their stick length.
 * Your output will either be true or false, to represent
 * whether you could make one square using all the matchsticks the little match girl has.
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * 
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MatchsticksSquare {

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int max = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            if (max > nums[i]) {
                max = nums[i];
            }
        }
    }
}
