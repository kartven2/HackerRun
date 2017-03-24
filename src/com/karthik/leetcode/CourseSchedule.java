/*
 * LeetCode Problem : https://leetcode.com/problems/course-schedule/#/description
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] pre) {
        if (numCourses == 0 || pre == null || pre.length == 0) {
            return true;
        }
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            if (adj.get(pre[i][1]) == null) {
                adj.put(pre[i][1], new HashSet<>());
            }
            adj.get(pre[i][1]).add(pre[i][0]);
        }
        boolean[] marked = new boolean[numCourses];
        Boolean[] memo = new Boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!marked[i]) {
                memo[i] = dfs(adj, i, marked, memo);
                if (memo[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> adj, int v, boolean[] marked, Boolean[] memo) {
        if (memo[v] != null) {
            return memo[v];
        }
        if (marked[v]) {
            return true;
        }
        if (adj.get(v) == null) {
            return false;
        }
        marked[v] = true;
        for (int w : adj.get(v)) {
            boolean hasCycle = dfs(adj, w, marked, memo);
            memo[w] = hasCycle;
            if (hasCycle) {
                return true;
            }
        }
        marked[v] = false;
        return false;
    }
}
