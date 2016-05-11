/*-
 * HackerRun problem :
 * https://www.hackerrank.com/challenges/bfsshortreach
 *
 * Find shortest path in a weighted undirected graph.
 * 
 */
package com.karthik.hackerrank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ShortestReach {

    private int noOfTests;

    private int n;

    private int m;

    private int s;

    private Scanner sc;

    private Graph G;

    private Bfs bfs;

    private List<String> output;

    private void input() {
        sc = new Scanner(System.in);
        noOfTests = sc.nextInt();
    }

    private void output() {
        for (String x : output) {
            System.out.println(x);
        }
    }

    private void compute() {
        output = new ArrayList<>();
        for (int i = 0; i < noOfTests; i++) {
            n = sc.nextInt();
            G = new Graph(n);
            m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                G.addEdge(sc.nextInt(), sc.nextInt());
            }
            s = sc.nextInt();
            bfs = new Bfs(G, s);
            StringBuilder testResult = new StringBuilder();
            for (int k = 1; k <= n; k++) {
                if (k != s) {
                    testResult.append(bfs.getShortestDistTo(k));
                    testResult.append(" ");
                }
            }
            output.add(testResult.toString());
        }
    }

    private class Graph {

        private int V;
        private int E;
        private List<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.adj = (List<Integer>[]) new ArrayList[this.V + 1];
            for (int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        public List<Integer> adj(int v) {
            return adj[v];
        }

        public int V() {
            return V;
        }
    }

    private class Bfs {

        private boolean[] marked;

        private int[] edgeTo;

        private int s;

        public Bfs(Graph G, int s) {

            this.s = s;
            marked = new boolean[G.V() + 1];
            edgeTo = new int[G.V() + 1];
            Queue<Integer> queue = new LinkedList<>();
            marked[s] = true;
            queue.add(s);

            while (!queue.isEmpty()) {
                int v = queue.remove();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        marked[w] = true;
                        edgeTo[w] = v;
                        queue.add(w);
                    }
                }
            }
        }

        public int getShortestDistTo(int v) {
            if (!marked[v]) {
                return -1;
            }

            int sum = 0;
            for (int x = v; x != s; x = edgeTo[x]) {
                sum += 6;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ShortestReach shortestReach = new ShortestReach();
        shortestReach.input();
        shortestReach.compute();
        shortestReach.output();
    }
}
