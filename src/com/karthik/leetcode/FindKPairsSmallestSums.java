/*
 * LeetCode: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/#/description
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindKPairsSmallestSums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums1.length == 0
                || nums2 == null || nums2.length == 0 || k <= 0) {
            return result;
        }
        int n = nums1.length, m = nums2.length;
        k = Math.min(k, n * m);
        int[] a = new int[n];
        for (int l = 0; l < k; l++) {
            int min = Integer.MAX_VALUE;
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] < m && nums1[i] + nums2[a[i]] < min) {
                    min = nums1[i] + nums2[a[i]];
                    t = i;
                }
            }
            int[] sub = {nums1[t], nums2[a[t]]};
            result.add(sub);
            a[t]++;
        }
        return result;
    }

    class Pair implements Comparable<Pair> {

        private int idx;
        private long sum;
        private int[] sub;

        Pair(int idx, int n1, int n2) {
            this.idx = idx;
            this.sum = n1 + n2;
            this.sub = new int[]{n1, n2};
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.sum, o.sum);
        }

    }

    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums1.length == 0
                || nums2 == null || nums2.length == 0 || k <= 0) {
            return result;
        }
        int n = nums1.length, m = nums2.length;
        k = Math.min(k, n * m);
        PriorityQueue<Pair> hp = new PriorityQueue<>();
        for (int i = 0; i < Math.min(k, m); i++) {
            hp.add(new Pair(0, nums1[0], nums2[i]));
        }
        for (int i = 0; i < k && !hp.isEmpty(); i++) {
            Pair p = hp.remove();
            result.add(p.sub);
            if (p.idx < n - 1) {
                hp.add(new Pair(p.idx + 1, nums1[p.idx + 1], p.sub[1]));
            }
        }
        return result;
    }
}
