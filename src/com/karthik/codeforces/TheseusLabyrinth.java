/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/676/D
 *
 * If Theseus is not able to get to Minotaur, then print -1 in the only line of the output.
 * Otherwise, print the minimum number of minutes required to get to the block where Minotaur is hiding.
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
public class TheseusLabyrinth {

    private int[][] vertexType;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/theseuslabyrinth4"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int m = sc.nextInt();
        vertexType = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = sc.readNext();
            char[] chars = line.toCharArray();
            for (int j = 1; j <= m; j++) {
                vertexType[i][j] = getType(chars[j - 1]);
            }
        }

        Graph g = new Graph(n, m, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        g.addEdges();
        //g.printArray();
        int output = g.djikstraSp() ? g.simulate() : -1;
        System.out.println(output);
    }

    class Graph {

        private static final int MAX = Integer.MAX_VALUE;
        private int n;
        private int m;
        private int V;
        private List<Integer>[] adj;
        private int source;
        private int dest;
        private int[][] weight;
        private int[] distTo;
        private int[] edgeTo;
        private IndexMinPQ<Integer> pq;

        Graph(int n, int m, int sx, int sy, int dx, int dy) {
            this.n = n;
            this.m = m;
            source = getIdx(sx, sy);
            dest = getIdx(dx, dy);
            if (source == dest) {
                System.out.println(0);
                System.exit(0);
            }
            V = (n * m);
            weight = new int[V + 1][V + 1];
            adj = new ArrayList[V + 1];
            distTo = new int[V + 1];
            edgeTo = new int[V + 1];
            for (int v = 1; v <= V; v++) {
                adj[v] = new ArrayList<>();
                distTo[v] = MAX;
            }
            distTo[source] = 0;
        }

        private int getIdx(int x, int y) {
            return (x - 1) * m + y;
        }

        private void cheapestEdge(int v, int sx, int sy, int dx, int dy, int wpos) {
            int w = getIdx(dx, dy);
            int vtype = vertexType[sx][sy];
            int wtype = vertexType[dx][dy];
            int cost = 0;
            boolean isReachable = false;
            for (int k = 1; k <= 4; k++) {
                switch (wpos) {
                    case 1:
                        isReachable = (vtype & 8) == 8 && (wtype & 2) == 2;
                        break;
                    case 2:
                        isReachable = (vtype & 4) == 4 && (wtype & 1) == 1;
                        break;
                    case 3:
                        isReachable = (vtype & 2) == 2 && (wtype & 8) == 8;
                        break;
                    case 4:
                        isReachable = (vtype & 1) == 1 && (wtype & 4) == 4;
                        break;
                }
                if (isReachable) {
                    cost++;
                    break;
                }
                vtype = (vtype >>> 1) | (vtype << (4 - 1));
                int tmp = vtype >>> 4;
                tmp = tmp << 4;
                vtype = vtype ^ tmp;
                wtype = (wtype >>> 1) | (wtype << (4 - 1));
                tmp = wtype >>> 4;
                tmp = tmp << 4;
                wtype = wtype ^ tmp;
                cost++;
            }
            if (isReachable) {
                weight[v][w] = cost;
                adj[v].add(w);
            }
        }

        private void addEdges() {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    int v = getIdx(i, j);

                    if ((i - 1) > 0) {
                        cheapestEdge(v, i, j, i - 1, j, 1);
                    }

                    if ((j - 1) > 0) {
                        cheapestEdge(v, i, j, i, j - 1, 4);
                    }

                    if ((i + 1) <= n) {
                        cheapestEdge(v, i, j, i + 1, j, 3);
                    }

                    if ((j + 1) <= m) {
                        cheapestEdge(v, i, j, i, j + 1, 2);
                    }
                }
            }
        }

        private void printArray() {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    System.out.print(weight[i][j] + " ");
                }
                System.out.println();
            }
        }

        private boolean djikstraSp() {
            pq = new IndexMinPQ<>(V);
            pq.insert(source, distTo[source]);
            while (!pq.isEmpty()) {
                int v = pq.delMin();
                for (int w : adj[v]) {
                    if (distTo[w] > distTo[v] + weight[v][w]) {
                        distTo[w] = distTo[v] + weight[v][w];
                        edgeTo[w] = v;
                        if (!pq.contains(w)) {
                            pq.insert(w, distTo[w]);
                        } else {
                            pq.decreaseKey(w, distTo[w]);
                        }
                    }
                }
            }
            return distTo[dest] < MAX;
        }

        private int simulate() {
            int[] path = new int[V];
            int pathPtr = 0;
            for (int x = dest; x != source; x = edgeTo[x]) {
                path[pathPtr++] = x;
            }
            int count = weight[source][path[pathPtr - 1]];
            int noOfRot = count - 1;
            while (pathPtr > 1) {
                int v = path[--pathPtr];
                int w = path[pathPtr - 1];
                int effortToMoveToW = weight[v][w] - noOfRot;
                noOfRot += (effortToMoveToW - 1);
                noOfRot %= 4;
                count += effortToMoveToW;
            }
            return count;
        }
    }

    class IndexMinPQ<Key extends Comparable<Key>> {

        private int maxN;
        private int N;
        private int[] pq;
        private int[] qp;
        private Key[] keys;

        IndexMinPQ(int maxN) {
            this.maxN = maxN;
            keys = (Key[]) new Comparable[maxN + 1];
            pq = new int[maxN + 1];
            qp = new int[maxN + 1];
            for (int i = 0; i < qp.length; i++) {
                qp[i] = -1;
            }
        }

        private boolean isEmpty() {
            return N == 0;
        }

        private boolean contains(int i) {
            return qp[i] != -1;
        }

        private void insert(int i, Key key) {
            N++;
            qp[i] = N;
            pq[N] = i;
            keys[i] = key;
            swim(N);
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exchange(k, k / 2);
                k = k / 2;
            }
        }

        private int delMin() {
            int min = pq[1];
            exchange(1, N--);
            sink(1);
            keys[min] = null;
            qp[min] = -1;
            pq[N + 1] = -1;
            return min;
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && greater(j, j + 1)) {
                    j++;
                }
                if (greater(j, k)) {
                    break;
                }
                exchange(k, j);
                k = j;
            }
        }

        private void decreaseKey(int i, Key key) {
            keys[i] = key;
            swim(qp[i]);
        }

        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exchange(int i, int j) {
            int tmp = pq[i];
            pq[i] = pq[j];
            pq[j] = tmp;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }

            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private int getType(char c) {
        //top-right-bottom-left open:1 close:0
        switch (c) {
            case '+':
                return 15;
            case '-':
                return 5;
            case '|':
                return 10;
            case '^':
                return 8;
            case '>':
                return 4;
            case '<':
                return 1;
            case 'v':
                return 2;
            case 'L':
                return 14;
            case 'R':
                return 11;
            case 'U':
                return 7;
            case 'D':
                return 13;
            case '*':
                return 0;
            default:
                throw new IllegalArgumentException("invalid input");
        }
    }

    public static void main(String... args) {
        TheseusLabyrinth tl = new TheseusLabyrinth();
        tl.compute();
    }
}
