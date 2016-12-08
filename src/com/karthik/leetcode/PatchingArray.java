/*-
 * Codeforces problem :
 * https://leetcode.com/problems/patching-array/
 *
 * Given a sorted positive integer array nums and an integer n,
 * add/patch elements to the array such that any number in range [1, n] inclusive
 * can be formed by the sum of some elements in the array.
 * Return the minimum number of patches required.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PatchingArray {

    public int minPatches(int[] nums, int n) {
        if (nums == null || n == 0) {
            return 0;
        }
        int ans = 0, i = 0;
        long sum = 0;
        while (sum < n) {
            if (i < nums.length && nums[i] <= sum + 1) {
                sum += nums[i++];
            } else {
                sum += (sum + 1);
                ans++;
            }
        }
        return ans;
    }
}
