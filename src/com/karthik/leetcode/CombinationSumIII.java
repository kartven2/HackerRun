/*
 * LeetCode: https://leetcode.com/problems/combination-sum-iii/description/
 *
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should
 * be a unique set of numbers.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= Math.min(9, n); i++) {
            if (!set.contains(i)) {
                collect(result, set, i, k, n, new ArrayList<>());
            }
        }
        return result;
    }

    private void collect(List<List<Integer>> result, Set<Integer> set, int start, int k, int bal, List<Integer> list) {
        if (bal == 0 && k == 0) {
            result.add(new ArrayList<>(list));
            for (int x : list) {
                set.add(x);
            }
            return;
        }
        for (int i = start; i <= Math.min(9, bal) && k > 0; i++) {
            if ((bal - i) >= 0) {
                list.add(i);
                collect(result, set, i + 1, k - 1, bal - i, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
