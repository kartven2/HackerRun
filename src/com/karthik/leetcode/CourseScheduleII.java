/*
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CourseScheduleII {

    class Graph {

        private int V;
        private List<Integer>[] adj;
        private int[] indegree;

        Graph(int V) {
            this.V = V;
            this.adj = (List<Integer>[]) new LinkedList[V];
            this.indegree = new int[V];
        }

        private void addEdge(int v, int w) {
            if (adj[v] == null) {
                adj[v] = new LinkedList<>();
            }
            adj[v].add(w);
            indegree[w]++;
        }

        private List<Integer> adj(int v) {
            return adj[v] == null ? new LinkedList<>() : adj[v];
        }
    }

    class TopologicalSort {

        private int[] order;
        private int oc;

        TopologicalSort(Graph G) {
            Queue<Integer> q = new LinkedList<>();
            order = new int[G.V];
            for (int v = 0; v < G.V; v++) {
                if (G.indegree[v] == 0) {
                    q.add(v);
                }
            }
            int count = 0;
            for (; !q.isEmpty();) {
                int v = q.remove();
                order[oc++] = v;
                count++;
                for (int w : G.adj(v)) {
                    G.indegree[w]--;
                    if (G.indegree[w] == 0) {
                        q.add(w);
                    }
                }
            }

            if (count != G.V) {
                order = new int[]{};
            }
        }
    }

    public int[] findOrder(int v, int[][] a) {
        Graph G = new Graph(v);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            G.addEdge(a[i][1], a[i][0]);
        }
        TopologicalSort ts = new TopologicalSort(G);
        return ts.order;
    }
}
