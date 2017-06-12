/*
 * LeetCode Problem: https://leetcode.com/problems/combinations/#/description
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Combinations {

    public static void main(String... args) {
        Combinations c = new Combinations();
        List<List<Integer>> res = c.combine(4, 2);
        for (List<Integer> lst : res) {
            for (int x : lst) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        collect(result, new LinkedList<>(), n, k, 1);
        return result;
    }

    private void collect(List<List<Integer>> result, List<Integer> list, int n, int k, int start) {
        if (k == 0) {
            result.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            collect(result, list, n, k - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
