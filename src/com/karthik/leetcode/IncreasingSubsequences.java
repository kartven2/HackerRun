/*
 * LeetCode problem : https://leetcode.com/problems/increasing-subsequences/#/description
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing
 * subsequence should be at least 2 .
 *
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers
 * should also be considered as a special case of increasing sequence.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] a) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        build(result, new LinkedList<>(), a, 0);
        return result;
    }

    private void build(List<List<Integer>> result, List<Integer> sub, int[] a, int start) {
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < a.length; i++) {
            if (set.contains(a[i])) {
                continue;
            }
            if (!sub.isEmpty() && sub.get(sub.size() - 1) > a[i]) {
                continue;
            }
            List<Integer> list = new LinkedList<>(sub);
            list.add(a[i]);
            if (list.size() > 1) {
                result.add(list);
            }
            set.add(a[i]);
            if (i + 1 < a.length) {
                build(result, list, a, i + 1);
            }
        }
    }
}
