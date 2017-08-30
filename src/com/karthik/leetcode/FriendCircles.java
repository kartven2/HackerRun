/*
 * Leetcode: https://leetcode.com/problems/friend-circles/description/
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C,
 * then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FriendCircles {

    static class Graph {

        List<Integer>[] adj;
        boolean[] marked;
        int cc, v;

        Graph(int[][] a) {
            int n = a.length, m = a[0].length;
            v = n;
            adj = (List<Integer>[]) new ArrayList[n];
            marked = new boolean[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (a[i][j] == 1) {
                        if (adj[i] == null) {
                            adj[i] = new ArrayList<>();
                        }
                        adj[i].add(j);
                    }
                }
            }
        }

        private void dfs() {
            for (int i = 0; i < v; i++) {
                if (!marked[i]) {
                    cc++;
                    dfs(i);
                }
            }
        }

        private void dfs(int v) {
            marked[v] = true;
            if (adj[v] != null) {
                for (int w : adj[v]) {
                    if (!marked[w]) {
                        dfs(w);
                    }
                }
            }
        }
    }

    public int findCircleNum(int[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Graph g = new Graph(a);
        g.dfs();
        return g.cc;
    }
}
