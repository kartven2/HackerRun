/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/690/C2
 *
 * Print one number – the brain latency.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
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
public class BrainNetwork2 {

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

    class Dequeue {

        private int[] array;
        private int f;
        private int r;
        private int sz;
        private int c;

        Dequeue() {
            array = new int[(int) 1e5 + 5];
            f = 1;
            c = (int) 1e5 + 4;
        }

        void inLast(int v) {
            array[r--] = v;
            r = (r % c + c) % c;
            sz++;
        }

        boolean isEmpty() {
            return sz == 0;
        }

        int outFirst() {
            f--;
            f = (f % c + c) % c;
            sz--;
            return array[f];
        }
    }

    class Bfs {

        private boolean[] marked;
        private int[] edgeTo;
        private int[] distTo;
        private Dequeue q;
        private Graph G;
        private int lastVisited;

        Bfs(Graph G, int s) {
            this.G = G;
            marked = new boolean[G.V + 1];
            edgeTo = new int[G.V + 1];
            distTo = new int[G.V + 1];
            q = new Dequeue();
            marked[s] = true;
            q.inLast(s);
            while (!q.isEmpty()) {
                int v = q.outFirst();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        marked[w] = true;
                        q.inLast(w);
                        lastVisited = w;
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                    }
                }
            }
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
        int j = g.E;
        while (j-- > 0) {
            g.addEdge(sc.nextInt(), sc.nextInt());
        }
        Bfs bfs = new Bfs(g, 1);
        int u = bfs.lastVisited;
        bfs = new Bfs(g, u);
        System.out.print(bfs.distTo[bfs.lastVisited]);
    }

    public static void main(String... args) {
        BrainNetwork2 bn2 = new BrainNetwork2();
        bn2.compute();
    }
}
