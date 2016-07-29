/*-
 * Codeforces problem :
 * http://codeforces.com/contest/682/problem/C
 *
 * Print the only integer — the minimum number of leaves
 * Alyona needs to remove such that there will be no any sad vertex left in the tree.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AlyonaTree {

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    class Edge {

        private int v;
        private int w;
        private int weight;

        Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        int other(int a) {
            if (a == v) {
                return w;
            }
            return v;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null || !(other instanceof Edge)) {
                return false;
            }
            if (this == other) {
                return true;
            }
            Edge e = (Edge) other;
            return (this.v == e.v && this.w == e.w && this.weight == e.weight);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 19 * hash + this.v;
            hash = 19 * hash + this.w;
            hash = 19 * hash + this.weight;
            return hash;
        }
    }

    class Graph {

        private int V;
        private int E;
        private List<Edge>[] adj;
        private int[] ai;

        Graph(int nv, int ne) {
            V = nv;
            E = ne;
            adj = new ArrayList[nv + 1];
            for (int v = 1; v <= V; v++) {
                adj[v] = new ArrayList<>();
            }
            ai = new int[nv + 1];
        }

        void addEdge(int v, int w, int weight) {
            E++;
            Edge e = new Edge(v, w, weight);
            adj[v].add(e);
            adj[w].add(e);
        }
    }

    class Dfs {

        private Graph g;

        Dfs(Graph g) {
            this.g = g;
        }

        private int dfs(int u, int v, long dist) {
            if (dist > g.ai[v]) {
                return 0;
            }
            int ans = 1;
            for (Edge e : g.adj[v]) {
                int w = e.other(v);
                if (w != u) {
                    ans += dfs(v, w, Math.max(0, dist + e.weight));
                }
            }
            return ans;
        }
    }

    void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/alyonatree2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int v = sc.nextInt();
        Graph g = new Graph(v, v - 1);
        for (int i = 1; i <= v; i++) {
            g.ai[i] = sc.nextInt();
        }
        for (int i = 1; i < v; i++) {
            g.addEdge(i + 1, sc.nextInt(), sc.nextInt());
        }
        Dfs df = new Dfs(g);
        System.out.print(v - df.dfs(0, 1, 0));
    }

    public static void main(String... args) {
        AlyonaTree at = new AlyonaTree();
        at.compute();
    }
}
