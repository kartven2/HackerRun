/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/687/A
 *
 * If it's impossible to split the graph between Pari and Arya as they expect, print "-1" (without quotes).
 * If there are two disjoint sets of vertices, such that both sets are vertex cover, print their descriptions.
 * Each description must contain two lines. The first line contains a single integer k denoting the number of
 * vertices in that vertex cover, and the second line contains k integers — the indices of vertices.
 * Note that because of m ≥ 1, vertex cover cannot be empty.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NPHardProblem {

    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    //private InputReader sc = new InputReader(System.in);
    private InputReader sc;

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/nphardproblem"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt(), m = sc.nextInt();
        Graph g = new Graph(n);
        for (; m > 0; m--) {
            g.addEdge(sc.nextInt() - 1, sc.nextInt() - 1);
        }
        Bipartite bp = new Bipartite(g);
        if (!bp.isBipartite) {
            pw.print("-1");
            pw.flush();
            return;
        }
        int[] a = new int[n];
        int[] p = new int[n];
        int x = 0, y = 0;
        for (int v = 0; v < n; v++) {
            if (bp.color[v]) {
                a[x++] = v + 1;
            } else {
                p[y++] = v + 1;
            }
        }
        pw.println(x);
        for (int i = 0; i < x; i++) {
            pw.print(a[i] + " ");
        }
        pw.println();
        pw.println(y);
        for (int i = 0; i < y; i++) {
            pw.print(p[i] + " ");
        }
        pw.flush();
    }

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

    class Graph {

        private int V;
        private List<Integer>[] adj;

        Graph(int V) {
            this.V = V;
            this.adj = (List<Integer>[]) new ArrayList[V];
        }

        private void addEdge(int v, int w) {
            if (adj[v] == null) {
                adj[v] = new ArrayList<>();

            }
            if (adj[w] == null) {
                adj[w] = new ArrayList<>();
            }
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    class Bipartite {

        private static final boolean A = true;
        private static final boolean P = !A;

        private boolean[] marked;
        private boolean[] color;
        private boolean isBipartite;

        Bipartite(Graph G) {
            marked = new boolean[G.V];
            color = new boolean[G.V];
            isBipartite = true;
            for (int v = 0; v < G.V && isBipartite; v++) {
                if (!marked[v]) {
                    bfs(G, v);
                }
            }
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> q = new LinkedList<>();
            marked[s] = true;
            color[s] = A;
            q.add(s);
            while (!q.isEmpty()) {
                int v = q.remove();
                if (G.adj[v] != null) {
                    for (int w : G.adj[v]) {
                        if (!marked[w]) {
                            marked[w] = true;
                            color[w] = !color[v];
                            q.add(w);
                        } else if (color[v] == color[w]) {
                            isBipartite = false;
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String... args) {
        NPHardProblem nph = new NPHardProblem();
        nph.compute();
    }
}
