/*
 * Leetcode: https://leetcode.com/problems/evaluate-division/#/description
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EvaluateDivision {

    private Map<String, List<String>> relation = new HashMap<>();
    private Map<String, Double> value = new HashMap<>();

    public double[] calcEquation(String[][] eq, double[] val, String[][] qrs) {
        if (eq == null || qrs == null || val == null || eq.length == 0 || val.length == 0 || qrs.length == 0) {
            return null;
        }
        int n = qrs.length, m = eq.length;
        for (int i = 0; i < m; i++) {
            String num = eq[i][0], den = eq[i][1];
            List<String> nlist = relation.get(num);
            if (nlist == null) {
                nlist = new LinkedList<>();
            }
            nlist.add(den);
            relation.put(num, nlist);
            List<String> dlist = relation.get(den);
            if (dlist == null) {
                dlist = new LinkedList<>();
                relation.put(den, dlist);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(num).append("$").append(den);
            value.put(sb.toString(), val[i]);
            if (val[i] != 0) {
                sb = new StringBuilder();
                sb.append(den).append("$").append(num);
                value.put(sb.toString(), (double) (1 / val[i]));
                relation.get(den).add(num);
            }
        }
        double[] ans = new double[n];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Double y = dfs(qrs[i][0], qrs[i][1], set);
            ans[i] = y == null ? -1 : y;
        }
        return ans;
    }

    private Double dfs(String a, String b, Set<String> visited) {
        visited.add(a);
        List<String> rlist = relation.get(a);
        if (rlist == null) {
            visited.remove(a);
            return null;
        }
        for (String den : rlist) {
            String x = a + "$" + den;
            if (den.equalsIgnoreCase(b)) {
                visited.remove(a);
                return value.get(x);
            } else if (!visited.contains(den)) {
                Double val = dfs(den, b, visited);
                if (val != null) {
                    visited.remove(a);
                    return val * value.get(x);
                }
            }
        }
        visited.remove(a);
        return null;
    }
}
