/*
 * Leetcode: https://leetcode.com/problems/subsets-ii/#/description
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * 
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubsetsII {

    public static void main(String... args) {
        SubsetsII sb = new SubsetsII();
        int[] a = {1, 2, 2};
        List<List<Integer>> res = sb.subsetsWithDup(a);
        for (int i = 0; i < res.size(); i++) {
            StringBuilder out = new StringBuilder();
            for (int x : res.get(i)) {
                out.append(x);
                out.append(",");
            }
            System.out.println("------");
            System.out.println(out.toString());
            System.out.println("------");
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] a) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        int n = a.length;
        Arrays.sort(a);
        build(result, new LinkedList<>(), a, 0, n);
        return result;
    }

    private void build(List<List<Integer>> res, LinkedList<Integer> list, int[] a, int start, int n) {
        if (start <= n) {
            res.add(list);
        }
        for (int i = start; i < n; i++) {
            if (i > start && a[i] == a[i - 1]) {
                continue;
            }
            list.add(a[i]);
            build(res, new LinkedList<>(list), a, i + 1, n);
            list.remove(list.size() - 1);
        }
    }
}
