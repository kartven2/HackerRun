/*
 * LeetCode: https://leetcode.com/problems/wiggle-sort-ii/
 *
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]
 *
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WiggleSort {

    private int[] a;
    private int n;

    private void exchange(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int partition(int lo, int hi) {
        int v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            while (a[--j] > v) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(i, j);
        }
        exchange(lo, j);
        return j;
    }

    private int smallest(int lo, int hi, int k) {
        while (true) {
            int m = partition(lo, hi);
            if (k == m) {
                return a[m];
            } else if (k < m) {
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }
    }

    private int map(int i) {
        return (i * 2 + 1) % (n | 1);
    }

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        a = nums;
        n = nums.length;
        int k = (n - 1) >> 1;
        int mid = smallest(0, n - 1, k);
        int c = 0, l = 0, r = n - 1;
        while (c <= r) {
            if (a[map(c)] > mid) {
                exchange(map(c), map(l));
                c++;
                l++;
            } else if (a[map(c)] < mid) {
                exchange(map(c), map(r));
                r--;
            } else {
                c++;
            }
        }
    }
}
