/*
 * LeetCode Problem: https://leetcode.com/problems/output-contest-matches/description/
 *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class OutputContestMatches {

    public String findContestMatch(int n) {
        if (n <= 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i + "");
        }
        while (list.size() > 1) {
            list = build(list);
        }
        return list.get(0);
    }

    private List<String> build(List<String> lst) {
        int i = 0, j = lst.size() - 1;
        List<String> rslt = new ArrayList<>();
        while (i < j) {
            String x = "(" + lst.get(i++) + "," + lst.get(j--) + ")";
            rslt.add(x);
        }
        return rslt;
    }
}
