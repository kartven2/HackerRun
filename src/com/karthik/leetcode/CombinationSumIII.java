/*
 * LeetCode: https://leetcode.com/problems/combination-sum-iii/description/
 *
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should
 * be a unique set of numbers.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CombinationSumIII {

    private int min(int a, int b) {
        return a <= b ? a : b;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return result;
        }
        collect(result, 1, k, n, new ArrayList<>());
        return result;
    }

    private void collect(List<List<Integer>> result, int start, int k, int bal, List<Integer> list) {
        if (bal == 0 && k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= min(9, bal) && k > 0; i++) {
            if ((bal - i) >= 0) {
                list.add(i);
                collect(result, i + 1, k - 1, bal - i, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
