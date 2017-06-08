/*
 * Leetcode: https://leetcode.com/problems/kth-largest-element-in-an-array/#/description
 *
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
package com.karthik.leetcode;

import java.util.Random;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KLargestElementArray {

    private void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void shuffle(int[] a) {
        int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            int r = (new Random()).nextInt(i + 1);
            exchange(a, i, r);
        }
    }

    private int partition(int[] a, int lo, int hi) {
        int v = a[lo], lt = lo, gt = hi, i = lt + 1;
        while (i <= gt) {
            if (a[i] > v) {
                exchange(a, i++, lt++);
            } else if (a[i] < v) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        return lt;
    }

    public int findKthLargest(int[] a, int k) {
        if (a == null || a.length == 0) {
            return 0;
        }
        shuffle(a);
        k--;
        int n = a.length, lo = 0, hi = n - 1, m = 0;
        while (lo < hi) {
            m = partition(a, lo, hi);
            if (m == k) {
                return a[m];
            } else if (m < k) {
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }
        return a[lo];
    }
}
