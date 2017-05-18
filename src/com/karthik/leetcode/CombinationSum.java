/*-
 * LeetCode Problem: https://leetcode.com/problems/combination-sum/#/description
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] a, int x) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        Arrays.sort(a);
        build(a, result, x, x, new LinkedList<>(), 0);
        return result;
    }

    private void build(int[] a, List<List<Integer>> result, int x, int tgt, List<Integer> sub, int start) {
        if (x == 0) {
            result.add(sub);
            return;
        }
        if (x < a[0]) {
            return;
        }
        for (int j = start; j < a.length; j++) {
            if (x - a[j] >= 0) {
                List<Integer> list = new LinkedList<>(sub);
                list.add(a[j]);
                build(a, result, x - a[j], tgt, list, j);
            }
        }
    }
}
