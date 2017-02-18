/*
 * LeetCode: https://leetcode.com/problems/minimum-height-trees/
 * 
 * For a undirected graph with tree characteristics,
 * we can choose any node as the root.
 * The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called
 * minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1.
 * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new LinkedList<>();
        if (n <= 0) {
            return result;
        }
        if (edges == null || edges.length == 0) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }
        List<Integer>[] adj = (List<Integer>[]) new LinkedList[n];
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            if (adj[edges[i][0]] == null) {
                adj[edges[i][0]] = new LinkedList<>();
            }
            if (adj[edges[i][1]] == null) {
                adj[edges[i][1]] = new LinkedList<>();
            }
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        int[] edgedist = new int[n];
        int[] mk = null;
        for (int i = 0; i < n; i++) {
            mk = new int[n];
            Arrays.fill(mk, -1);
            mk[i] = 0;
            dfs(i, adj, mk);
            edgedist[i] = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                edgedist[i] = Math.max(edgedist[i], mk[j]);
            }
        }
        int mindist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            mindist = Math.min(mindist, edgedist[i]);
        }
        for (int i = 0; i < n; i++) {
            if (mindist == edgedist[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int v, List<Integer>[] adj, int[] mk) {
        for (int w : adj[v]) {
            if (mk[w] < 0) {
                mk[w] = mk[v] + 1;
                dfs(w, adj, mk);
            }
        }
    }
}
