/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/690/C1
 *
 * The output consists of one line, containing either yes or no
 * depending on whether the nervous system is valid.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BrainNetwork {

    private static final String YES = "yes";
    private static final String NO = "no";

    class Graph {

        private int E;
        private int V;
        private List<Integer>[] adj;

        Graph(int nv, int ne) {
            V = nv;
            E = ne;
            adj = new ArrayList[V + 1];
            for (int v = 1; v <= V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        List<Integer> adj(int v) {
            return adj[v];
        }
    }

    class Dfs {

        private boolean[] marked;
        private int[] edgeTo;
        private Stack<Integer> cycle;
        private Graph G;

        Dfs(Graph G) {
            this.G = G;
            if (hasParallelEdge()) {
                return;
            }
            marked = new boolean[G.V + 1];
            edgeTo = new int[G.V + 1];
            for (int v = 1; v <= G.V; v++) {
                if (!marked[v]) {
                    dfs(-1, v);
                }
            }
        }

        private void dfs(int u, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (cycle != null) {
                    return;
                }
                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(v, w);
                } else if (u != w) {
                    cycle = new Stack<>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
        }

        private boolean isCycleExists() {
            return cycle != null;
        }

        private boolean isConnected() {
            for (int i = 1; i <= G.V; i++) {
                if (!marked[i]) {
                    return false;
                }
            }
            return true;
        }

        private boolean hasParallelEdge() {
            marked = new boolean[G.V + 1];
            for (int v = 1; v <= G.V; v++) {
                for (int w : G.adj(v)) {
                    if (marked[w]) {
                        cycle = new Stack<>();
                        cycle.push(v);
                        cycle.push(w);
                        cycle.push(v);
                        return true;
                    }
                    marked[w] = true;
                }
                for (int w : G.adj(v)) {
                    marked[w] = false;
                }
            }
            return false;
        }
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

    void compute() {
        InputReader sc = new InputReader(System.in);
        Graph g = new Graph(sc.nextInt(), sc.nextInt());
        if (g.E + 1 != g.V) {
            System.out.println(NO);
            return;
        }
        int j = g.E;
        while (j-- > 0) {
            g.addEdge(sc.nextInt(), sc.nextInt());
        }
        Dfs dfs = new Dfs(g);
        if (!dfs.isConnected() || dfs.isCycleExists()) {
            System.out.println(NO);
            return;
        }
        System.out.println(YES);
    }

    public static void main(String... args) {
        BrainNetwork bn = new BrainNetwork();
        bn.compute();
    }
}
