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
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumHeightTrees {

    private static final int MAX = Integer.MAX_VALUE;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new LinkedList<>();
        if (n <= 0) {
            return result;
        }
        if (edges == null || edges.length == 0 || n == 2 && edges.length == 1) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }
        List<Integer>[] adj = (List<Integer>[]) new LinkedList[n];
        int m = edges.length;
        int[] degree = new int[n];
        for (int i = 0; i < m; i++) {
            if (adj[edges[i][0]] == null) {
                adj[edges[i][0]] = new LinkedList<>();
            }
            if (adj[edges[i][1]] == null) {
                adj[edges[i][1]] = new LinkedList<>();
            }
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        int[] maxHeight = new int[n];
        Arrays.fill(maxHeight, MAX);
        for (int i = 0; i < n; i++) {
            if (degree[i] > 1) {
                maxHeight[i] = bfs(i, adj, n);
            }
        }
        int minHeight = MAX;
        for (int i = 0; i < n; i++) {
            if (maxHeight[i] < minHeight) {
                minHeight = maxHeight[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (maxHeight[i] == minHeight) {
                result.add(i);
            }
        }
        return result;
    }

    private int bfs(int s, List<Integer>[] adj, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] mk = new boolean[n];
        int[] distTo = new int[n];
        int maxDist = 0;
        q.add(s);
        mk[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : adj[v]) {
                if (!mk[w]) {
                    mk[w] = true;
                    q.add(w);
                    distTo[w] = distTo[v] + 1;
                    if (maxDist < distTo[w]) {
                        maxDist = distTo[w];
                    }
                }
            }
        }
        return maxDist;
    }
}
