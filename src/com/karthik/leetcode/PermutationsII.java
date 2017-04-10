/*
 * LeetCode: https://leetcode.com/problems/permutations-ii/#/description
 *
 * Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations: 
 * [ [1,1,2],
     [1,2,1],
     [2,1,1]
   ]
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PermutationsII {

    private void reverse(int[] a, int i, int j) {
        while (i < j) {
            exchange(a, i, j);
            i++;
            j--;
        }
    }

    private void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public List<List<Integer>> permuteUnique(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        int n = a.length;
        Arrays.sort(a);
        List<Integer> sub = new ArrayList<>();
        for (int x : a) {
            sub.add(x);
        }
        result.add(sub);
        if (n == 1) {
            return result;
        }
        while (true) {
            int i = n - 2, j = n - 1;
            while (i >= 0 && a[i + 1] <= a[i]) {
                i--;
            }
            if (i < 0) {
                return result;
            }
            while (j > i && a[j] <= a[i]) {
                j--;
            }
            exchange(a, i, j);
            reverse(a, i + 1, n - 1);
            sub = new ArrayList<>();
            for (int x : a) {
                sub.add(x);
            }
            result.add(sub);
        }
    }

    public static void main(String... args) {
        PermutationsII p2 = new PermutationsII();
        int[] a = {1, 1, 2};
        p2.permuteUnique(a);
    }
}
