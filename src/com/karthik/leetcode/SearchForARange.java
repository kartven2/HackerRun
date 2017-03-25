/*
 * LeetCode: https://leetcode.com/problems/search-for-a-range/#/description
 * Given an array of integers sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SearchForARange {

    private Integer binSearch(int lo, int hi, int[] a, int key) {
        if (key < a[lo] || key > a[hi]) {
            return null;
        }
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] < key) {
                lo = mid + 1;
            } else if (a[mid] > key) {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public int[] searchRange(int[] a, int key) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        if (a == null || a.length == 0 || key < a[0] || key > a[a.length - 1]) {
            return res;
        }
        int lo = 0, hi = a.length - 1;
        Integer mid = binSearch(lo, hi, a, key);

        if (mid == null || a[mid] != key) {
            return res;
        } else if (a[mid] == key) {
            Arrays.fill(res, mid);
        }

        int lo1 = mid - 1, hi1 = mid + 1;
        while (true) {
            if (lo <= lo1) {
                Integer lk = binSearch(lo, lo1, a, key);
                if (lk == null) {
                    lo1 = lo - 1;
                } else if (a[lk] == key) {
                    res[0] = lk;
                    lo1 = lk - 1;
                }
            }

            if (hi1 <= hi) {
                Integer hk = binSearch(hi1, hi, a, key);
                if (hk == null) {
                    hi1 = hi + 1;
                } else if (a[hk] == key) {
                    res[1] = hk;
                    hi1 = hk + 1;
                }
            }

            if (lo > lo1 && hi1 > hi) {
                break;
            }
        }

        return res;
    }

    public static void main(String... args) {
        SearchForARange sr = new SearchForARange();
        int[] a = {1, 2, 4, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 11};
        int[] rec = sr.searchRange(a, 8);
        System.out.println(rec[0] + " and " + rec[1]);
    }
}
