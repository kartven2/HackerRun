/*
 * LeetCode: https://leetcode.com/problems/circular-array-loop/#/description
 *
 * You are given an array of positive and negative integers.
 * If a number n at an index is positive, then move forward n steps.
 * Conversely, if it's negative (-n), move backward n steps.
 * Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element.
 * Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop.
 * The loop must be "forward" or "backward'.
 * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
 * Example 2: Given the array [-1, 2], there is no loop.
 * Note: The given array is guaranteed to contain no element "0".
 * Can you do it in O(n) time complexity and O(1) space complexity?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CircularArrayLoop {

    private int move(int i, int n, int[] a) {
        return (i + a[i] + n) % n;
    }

    public boolean circularArrayLoop(int[] a) {
        if (a == null || a.length < 3) {
            return false;
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            int j = i, k = i, sign = a[i];
            while ((sign * a[move(k, n, a)]) > 0 && (sign * a[move(move(k, n, a), n, a)]) > 0) {
                j = move(j, n, a);
                k = move(move(k, n, a), n, a);
                if (j == k) {
                    if (j == move(j, n, a)) {
                        break;
                    }
                    return true;
                }
            }
            j = i;
            while ((sign * a[j]) > 0) {
                int tmp = j;
                j = move(j, n, a);
                a[tmp] = 0;
            }
        }
        return false;
    }
}
