package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubsetSizeK {

    public static void main(String... args) {
        SubsetSizeK sk = new SubsetSizeK();
        int[] a = {1, 2, 3, 4, 5};
        List<List<Integer>> result = sk.generate(a, 3);
        for (List<Integer> list : result) {
            StringBuilder sb = new StringBuilder();
            for (int x : list) {
                sb.append(x);
                sb.append(",");
            }
            System.out.println(sb.toString());
        }
    }

    private List<List<Integer>> generate(int[] a, int k) {
        List<List<Integer>> result = new LinkedList<>();
        if (a == null || a.length == 0 || k <= 0 || k > a.length) {
            return result;
        }
        int n = a.length;
        build(result, new LinkedList<>(), a, n, 0, k);
        return result;
    }

    private void build(List<List<Integer>> result, List<Integer> list, int[] a, int n, int start, int k) {
        if (list.size() == k) {
            result.add(list);
            return;
        }
        for (int i = start; i < n && k - list.size() <= n - i; i++) {
            List<Integer> sub = new LinkedList<>(list);
            sub.add(a[i]);
            build(result, sub, a, n, i + 1, k);
        }
    }
}
