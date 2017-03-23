/*
 * LeetCode: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/#/description
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindKPairsSmallestSums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if(nums1==null || nums1.length==0
                || nums2==null || nums2.length==0 || k<=0) {
            return result;
        }
        int n = nums1.length, m= nums2.length, i=0, j=0;
        int[] p2 = new int[n];
        int[] p1 = new int[m];
        for(int l=0; l<k; l++) {
            int[] subres = new int[2];
            subres[0] = nums1[p1[j]++];
            subres[1] = nums2[p2[i]++];
            result.add(subres);
            while(p2[i]==m) {
                i++;
            }
            while(p1[j]==n) {
                j++;
            }
            if(nums1[p1[j]])
            
        }

    }
}
