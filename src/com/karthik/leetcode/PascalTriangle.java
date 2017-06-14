/*
 * Leetcode: https://leetcode.com/problems/pascals-triangle/#/description
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int n) {
        List<List<Integer>> result = new LinkedList<>();
        if (n <= 0) {
            return result;
        }
        List<Integer> plist = null;
        for (int i = 0; i < n; i++) {
            List<Integer> clist = new LinkedList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    clist.add(1);
                } else {
                    int sum = plist.get(j) + plist.get(j - 1);
                    clist.add(sum);
                }
            }
            result.add(clist);
            plist = clist;
        }
        return result;
    }
}
