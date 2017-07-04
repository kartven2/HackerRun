/*-
 * LeetCode Problem : https://leetcode.com/problems/random-pick-index/#/description
 * Given an array of integers with possible duplicates, randomly output the index
 * of a given target number. You can assume that the given target number must exist in the array.
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RandomPickIndex {

    private int[] a;
    private int n;
    private boolean isInvld;

    public RandomPickIndex(int[] a) {
        this.a = a;
        this.n = a == null ? 0 : a.length;
        this.isInvld = n == 0;
    }

    public int pick(int tgt) {
        if (isInvld) {
            return 0;
        }
        int freq = 0, res = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] == tgt) {
                int rand = (int) (Math.random() * (++freq));
                if (rand == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}
