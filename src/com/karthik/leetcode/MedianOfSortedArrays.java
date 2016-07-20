/*
 * Leetcode: https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MedianOfSortedArrays {

    public static void main(String... args) {
        MedianOfSortedArrays msa = new MedianOfSortedArrays();
        int[] nums1 = {};
        int[] nums2 = {0, 23};
        System.out.println(msa.findMedian(nums1, nums2));
    }

    private double mo2(double a, double b) {
        return (double) (a + b) / 2;
    }

    private double mo3(double a, double b, double c) {
        return (double) (a + b + c - Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c)));
    }

    private double mo4(double a, double b, double c, double d) {
        return (double) (a + b + c + d - Math.max(a, Math.max(b, Math.max(c, d))) - Math.min(a, Math.min(b, Math.min(c, d)))) / 2;
    }

    private double median(int[] arr, int N) {
        if (N == 0) {
            return (double) N;
        }
        if (N == 1) {
            return (double) arr[0];
        }
        int a = N / 2;
        if (N % 2 == 1) {
            return arr[a];
        } else {
            return (double) (arr[a] + arr[a - 1]) / 2;
        }
    }

    private double findMedian(int[] nums1, int[] nums2) {
        return findMedianUtil(nums1, nums1.length, nums2, nums2.length);
    }

    private double findMedianUtil(int[] A, int N, int[] B, int M) {
        if (N <= M) {
            return findMedian(A, N, B, M);
        }
        return findMedian(B, M, A, N);
    }

    private double findMedian(int[] A, int N, int[] B, int M) {
        if (N == 0) {
            if (M == 0) {
                return (double) N;
            }
            return median(B, M);
        }
        if (N == 1) {
            if (M == 1) {
                return mo2(A[0], B[0]);
            }
            if (M % 2 == 0) {
                return mo3(A[0], B[(M / 2) - 1], B[M / 2]);
            }
            return mo2(B[M / 2], mo3(A[0], B[(M / 2) - 1], B[(M / 2) + 1]));
        }
        if (N == 2) {
            if (M == 2) {
                return mo4(A[0], A[1], B[0], B[1]);
            }
            if (M % 2 == 0) {
                return mo4(B[M / 2], Math.min(A[1], B[(M / 2) + 1]), Math.max(A[0], B[(M / 2) - 2]), B[(M / 2) - 1]);
            }
            return mo3(B[M / 2], Math.max(A[0], B[(M / 2) - 1]), Math.min(A[1], B[(M / 2) + 1]));
        }
        int midAidx = (N - 1) / 2;
        int midBidx = (M - 1) / 2;
        if (A[midAidx] <= B[midBidx]) {
            return findMedianUtil(Arrays.copyOfRange(A, midAidx, N), (N / 2) + 1, B, M - midAidx);
        }
        return findMedianUtil(A, (N / 2) + 1, Arrays.copyOfRange(B, midAidx, M), M - midAidx);
    }
}
