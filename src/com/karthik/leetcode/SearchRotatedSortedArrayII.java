/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/#/description
 *
 * Suppose an array sorted in ascending order is rotated at some pivot
 * unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Write a function to determine if a given target is in the array.
 * The array may contain duplicates.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SearchRotatedSortedArrayII {

    public static void main(String... args) {
        SearchRotatedSortedArrayII sa = new SearchRotatedSortedArrayII();
        int[] a = {1, 3, 1, 1, 1};
        System.out.println(sa.search(a, 3));
    }

    public boolean search(int[] a, int tgt) {
        if (a == null || a.length == 0) {
            return false;
        }
        return search(a, 0, a.length - 1, tgt);
    }

    private boolean search(int[] a, int lo, int hi, int tgt) {
        if (lo > hi) {
            return false;
        }
        int mid = (lo + hi) >> 1;
        if (tgt == a[mid] || lo < a.length && tgt == a[lo] || hi >= 0 && tgt == a[hi]) {
            return true;
        }
        if (a[lo] == a[mid] && a[mid] == a[hi]) {
            return search(a, lo, mid - 1, tgt) || search(a, mid + 1, hi, tgt);
        } else if (a[lo] == a[mid]) {
            return search(a, mid + 1, hi, tgt);
        } else if (a[mid] == a[hi]) {
            return search(a, lo, mid - 1, tgt);
        }
        if (a[lo] < a[mid]) {
            if (tgt > a[lo] && tgt < a[mid]) {
                return search(a, lo, mid - 1, tgt);
            } else {
                return search(a, mid + 1, hi, tgt);
            }
        } else if (tgt < a[hi] && tgt > a[mid]) {
            return search(a, mid + 1, hi, tgt);
        } else {
            return search(a, lo, mid - 1, tgt);
        }
    }
}
