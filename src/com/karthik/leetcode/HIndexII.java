/*
 * LeetCode: https://leetcode.com/problems/h-index-ii/#/description
 * Given an array of citations (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * Follow up for H-Index: What if the citations array is sorted in ascending order?
 * Could you optimize your algorithm?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HIndexII {

    public static void main(String... args) {
        HIndexII hi = new HIndexII();
        int[] a = {1, 1, 3};
        hi.hIndex(a);
    }

    public int hIndex(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, lo = 0, hi = n - 1, mid, ans = 0;
        if (a[lo] >= n) {
            return n;
        }
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] == n - mid) {
                return a[mid];
            } else if (a[mid] > n - mid) {
                ans = n - mid;
                if (a[mid] < ans) {
                    ans = a[mid];
                }
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
