/*
 * LeetCode: https://leetcode.com/problems/combination-sum-ii/#/description
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] c, int t) {
        List<List<Integer>> result = new ArrayList<>();
        if (c == null || c.length == 0) {
            return result;
        }
        Arrays.sort(c);
        if (t < c[0]) {
            return result;
        }
        int lo = 0, hi = c.length - 1;
        List<Integer> subList = new ArrayList<>();
        buildSet(c, lo, hi, result, subList, t);
        return result;
    }

    private void buildSet(int[] c, int lo, int hi, List<List<Integer>> result, List<Integer> subList, int t) {
        if (t == 0) {
            List<Integer> sub = new ArrayList<>();
            for (int x : subList) {
                sub.add(x);
            }
            result.add(sub);
            return;
        }
        while (hi > lo && c[hi] > t) {
            hi--;
        }
        if (lo > hi) {
            return;
        }
        for (int i = lo; i <= hi; i++) {
            if (i > lo && c[i] == c[i - 1]) {
                continue;
            }
            Integer x = c[i];
            subList.add(x);
            buildSet(c, i + 1, hi, result, subList, t - c[i]);
            subList.remove(x);
        }
    }

    public static void main(String... args) {
        int[] a = {10, 1, 2, 7, 6, 1, 5};
        int t = 8;
        CombinationSumII cs = new CombinationSumII();
        List<List<Integer>> ans = cs.combinationSum2(a, t);
        System.out.println("Hello");
    }
}
