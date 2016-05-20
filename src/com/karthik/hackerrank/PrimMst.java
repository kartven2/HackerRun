/*-
 * HackerRank problem :
 * https://www.hackerrank.com/challenges/primsmstsub
 *
 * Find the weight of MST in an undirected graph.
 * 
 */
package com.karthik.hackerrank;

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
public class PrimMst {

    static class InputReader {

        private static final int inputKb = 10240;
        private StringTokenizer stringTokenizer;
        private BufferedReader bufferedReader;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), inputKb);
        }

        private String readNext() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return stringTokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    static class Edge {

        private int v;
        private int w;
        private int weight;

        Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        int other(int x) {
            if (x == v) {
                return w;
            }
            return v;
        }
    }

    static class Graph {

        private int vertices;
        private int edges;
        private List<Edge>[] adj;
        private int source;

        Graph(InputReader sc) {
            vertices = sc.nextInt();
            edges = sc.nextInt();
            adj = (List<Edge>[]) new ArrayList[vertices + 1];
            for (int v = 0; v <= vertices; v++) {
                adj[v] = new ArrayList<>();
            }
            for (int e = 0; e < edges; e++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                int weight = sc.nextInt();
                adj[from].add(new Edge(from, to, weight));
                adj[to].add(new Edge(to, from, weight));
            }
            source = sc.nextInt();
        }
    }

    static class IndexMinPq {

        private int maxN;
        private int[] pq;
        private int[] qp;
        private Long[] keys;
        private int n;

        IndexMinPq(int maxN) {
            this.maxN = maxN;
            keys = new Long[maxN];
            pq = new int[maxN];
            qp = new int[maxN];
            for (int i = 0; i < maxN; i++) {
                qp[i] = -1;
            }
        }

        private void insert(int i, long value) {
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = value;
            swim(n);
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exchange(k, k / 2);
                k = k / 2;
            }
        }

        private boolean greater(int x, int y) {
            return keys[pq[x]] > keys[pq[y]];
        }

        private void exchange(int x, int y) {
            int tmp = pq[x];
            pq[x] = pq[y];
            pq[y] = tmp;
            qp[pq[x]] = x;
            qp[pq[y]] = y;
        }

        private boolean isEmpty() {
            return n == 0;
        }

        private void decreaseKey(int x, long value) {
            keys[x] = value;
            swim(qp[x]);
        }

        private boolean contains(int x) {
            return qp[x] > -1;
        }

        private int deleteMin() {
            int min = pq[1];
            keys[min] = null;
            exchange(1, n);
            qp[min] = -1;
            pq[n] = -1;
            n--;
            sink(1);
            return min;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && greater(j, j + 1)) {
                    j++;
                }
                if (greater(j, k)) {
                    break;
                }
                exchange(j, k);
                k = j;
            }
        }
    }

    static class Prim {

        private static final long MAX = Long.MAX_VALUE;
        private long[] distTo;
        private boolean[] marked;
        private IndexMinPq minPq;
        private long result;

        Prim(Graph G, int s) {
            distTo = new long[G.vertices + 1];
            marked = new boolean[G.vertices + 1];
            minPq = new IndexMinPq(G.vertices + 1);

            for (int i = 1; i <= G.vertices; i++) {
                distTo[i] = MAX;
            }
            distTo[s] = 0;
            minPq.insert(s, distTo[s]);
            while (!minPq.isEmpty()) {
                int v = minPq.deleteMin();
                marked[v] = true;
                for (Edge e : G.adj[v]) {
                    int w = e.other(v);
                    if (!marked[w]) {
                        if (distTo[w] > e.weight) {
                            distTo[w] = e.weight;
                            if (minPq.contains(w)) {
                                minPq.decreaseKey(w, distTo[w]);
                            } else {
                                minPq.insert(w, distTo[w]);
                            }
                        }
                    }
                }
            }

            for (int i = 1; i <= G.vertices; i++) {
                if (marked[i]) {
                    result += distTo[i];
                }
            }

            System.out.print(result);
        }
    }

    public static void main(String[] args) {
        InputReader sc = new InputReader(System.in);
        Graph g = new Graph(sc);
        Prim p = new Prim(g, g.source);
    }
}
