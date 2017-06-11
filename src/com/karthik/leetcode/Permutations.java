/*
 * Leetcode: https://leetcode.com/problems/permutations/#/description
 *
 * Given a collection of distinct numbers, return all possible permutations.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Permutations {

    public static void main(String... args) {
        Permutations p = new Permutations();
        List<List<Integer>> result = p.permute(new int[]{1, 2, 3});
        for (List<Integer> lst : result) {
            for (int x : lst) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public List<List<Integer>> permute(int[] a) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        int n = a.length;
        build(result, a, 0, n);
        return result;
    }

    private void build(List<List<Integer>> result, int[] a, int j, int n) {
        if (j == n - 1) {
            List<Integer> list = new LinkedList<>();
            for (int x : a) {
                list.add(x);
            }
            result.add(list);
            return;
        }
        for (int i = j; i < n; i++) {
            swap(a, i, j);
            build(result, a, j + 1, n);
            swap(a, i, j);
        }
    }
}
