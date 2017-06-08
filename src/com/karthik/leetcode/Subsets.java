/*
 * Leetcode: https://leetcode.com/problems/subsets/#/description
 *
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Subsets {

    public static void main(String... args) {
        Subsets s = new Subsets();
        int[] a = {1, 2, 3};
        List<List<Integer>> result = s.subsets(a);
        for (List<Integer> row : result) {
            StringBuilder sb = new StringBuilder();
            for (int x : row) {
                sb.append(x);
                sb.append(" ");

            }
            System.out.println(sb.toString());
        }

    }

    public List<List<Integer>> subsets(int[] a) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        int n = a.length;
        boolean[] mark = new boolean[n];
        collect(result, mark, a, 0);
        return result;
    }

    private void collect(List<List<Integer>> result, boolean[] mark, int[] a, int start) {
        if (start == a.length) {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < mark.length; i++) {
                if (mark[i]) {
                    list.add(a[i]);
                }
            }
            result.add(list);
            return;
        }
        collect(result, mark, a, start + 1);
        mark[start] = true;
        collect(result, mark, a, start + 1);
        mark[start] = false;
    }
}
