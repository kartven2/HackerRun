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

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumHeightTrees {

    class BfsResult {

        private int lastVertex;
        private int[] edgeTo;

        BfsResult(int l, int[] et) {
            lastVertex = l;
            edgeTo = et;
        }
    }

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
        int v = bfs(0, adj, n).lastVertex;
        BfsResult bfsResult = bfs(v, adj, n);
        Stack<Integer> path = findPath(v, bfsResult.lastVertex, bfsResult.edgeTo);
        int sz = path.size();
        if (sz % 2 == 0) {
            for (int i = 0; i < (sz / 2) - 1; i++) {
                path.pop();
            }
            result.add(path.pop());
            result.add(path.pop());
            return result;
        }
        for (int i = 0; i < (sz / 2); i++) {
            path.pop();
        }
        result.add(path.pop());
        return result;
    }

    private BfsResult bfs(int s, List<Integer>[] adj, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] mk = new boolean[n];
        int[] edgeTo = new int[n];
        q.add(s);
        mk[s] = true;
        int lastVertex = -1;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : adj[v]) {
                if (!mk[w]) {
                    mk[w] = true;
                    q.add(w);
                    lastVertex = w;
                    edgeTo[w] = v;
                }
            }
        }
        return new BfsResult(lastVertex, edgeTo);
    }

    private Stack<Integer> findPath(int s, int d, int[] edgeTo) {
        Stack<Integer> path = new Stack<>();
        path.push(d);
        int x = d;
        while (edgeTo[x] != s) {
            x = edgeTo[x];
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
