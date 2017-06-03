/*
 * LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/#/description
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements
 * of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RemoveDuplicatesfromSortedArrayII {

    public int removeDuplicates2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int i = 0;
        for (int x : a) {
            if (i < 2 || a[i - 2] < x) {
                a[i++] = x;
            }
        }
        return i;
    }

    public int removeDuplicates(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, idx = 0, val = a[0], count = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1]) {
                if (count < 2) {
                    count++;
                }
            } else {
                for (int j = 0; j < count; j++) {
                    a[idx + j] = val;
                }
                val = a[i];
                idx += count;
                count = 1;
            }
        }
        for (int j = 0; j < count; j++) {
            a[idx + j] = val;
        }
        return idx + count;
    }
}
