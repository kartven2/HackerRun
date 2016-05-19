/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/667/D
 *
 * Find the total longest path visiting exactly 4 cities
 * such that we use shortest path between each city.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WorldTour {

    static class InputReader {

        private static final int inputkb = 10240;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), inputkb);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    static class WeightedEdge {

        private int from;
        private int to;
        private int weight;

        public WeightedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph {

        private int totalVertices;
        private int totalEdges;
        private List<WeightedEdge>[] adj;

        Graph(InputReader sc) {
            this.totalVertices = sc.nextInt();
            this.totalEdges = sc.nextInt();
            adj = (List<WeightedEdge>[]) new ArrayList[this.totalVertices + 1];
            for (int i = 1; i <= this.totalVertices; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int e = 0; e < totalEdges; e++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                adj[from].add(new WeightedEdge(from, to, 1));
            }
        }
    }

    static class Bfs {

        private static final int MAX = Integer.MAX_VALUE;
        private int distTo[];
        private boolean marked[];

        Bfs(Graph G, int s, int[][] adj) {
            distTo = new int[G.totalVertices + 1];
            marked = new boolean[G.totalVertices + 1];
            for (int v = 0; v <= G.totalVertices; v++) {
                distTo[v] = MAX;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            marked[s] = true;
            distTo[s] = 0;
            while (!queue.isEmpty()) {
                int v = queue.remove();
                for (WeightedEdge e : G.adj[v]) {
                    int w = e.to;
                    if (!marked[w]) {
                        distTo[w] = distTo[v] + e.weight;
                        marked[w] = true;
                        queue.add(w);
                    }
                }
            }

            for (int v = 1; v <= G.totalVertices; v++) {
                adj[s][v] = distTo[v] == MAX ? -1 : distTo[v];
            }
        }
    }

    static class City implements Comparable<City> {

        private int cityId;
        private int distance;

        public City(int cityId, int distance) {
            this.cityId = cityId;
            this.distance = distance;
        }

        @Override
        public int compareTo(City that) {
            return that.distance - this.distance;
        }
    }

    private int[][] adj;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream(new File("./resources/worldtour")));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        //Build Graph
        Graph G = new Graph(sc);
        adj = new int[G.totalVertices + 1][G.totalVertices + 1];

        //Run BFS from each vertex and fill adjacency matrix
        for (int v = 1; v <= G.totalVertices; v++) {
            Bfs bfs = new Bfs(G, v, adj);
        }

        //printAdjMatrix();
        // Find the farthest 3 cities to each city
        City[][] threeFurthestTo = new City[G.totalVertices + 1][4];
        // Find the farthest 3 cities from each city
        City[][] threeFurthestFrom = new City[G.totalVertices + 1][4];

        for (int i = 1; i <= G.totalVertices; i++) {
            City[] source = new City[G.totalVertices + 1];
            int sourceIdx = 1;
            City[] dest = new City[G.totalVertices + 1];
            int destIdx = 1;
            for (int j = 1; j <= G.totalVertices; j++) {
                if (adj[j][i] > 0) {
                    source[sourceIdx] = new City(j, adj[j][i]);
                    sourceIdx++;
                }
                if (adj[i][j] > 0) {
                    dest[destIdx] = new City(j, adj[i][j]);
                    destIdx++;
                }
            }
            Arrays.sort(source, 1, sourceIdx);
            threeFurthestTo[i][1] = source[1];
            threeFurthestTo[i][2] = source[2];
            threeFurthestTo[i][3] = source[3];
            Arrays.sort(dest, 1, destIdx);
            threeFurthestFrom[i][1] = dest[1];
            threeFurthestFrom[i][2] = dest[2];
            threeFurthestFrom[i][3] = dest[3];
        }

        //printMax3To(threeFurthestTo);
        //printMax3From(threeFurthestFrom);
        //For every path a -> x -> y -> b find the largest path
        int max = 0;
        int[] result = new int[4];
        for (int x = 1; x <= G.totalVertices; x++) {
            for (int y = 1; y <= G.totalVertices; y++) {
                if (adj[x][y] > 0) {
                    for (int a = 1; a < 4; a++) {
                        City start = threeFurthestTo[x][a];
                        if (start != null) {
                            for (int b = 1; b < 4; b++) {
                                City end = threeFurthestFrom[y][b];
                                if (end != null) {
                                    int startCityId = start.cityId;
                                    int endCityId = end.cityId;
                                    if (isDistinctCity(startCityId, x, y, endCityId)) {
                                        int sum = adj[startCityId][x] + adj[x][y] + adj[y][endCityId];
                                        if (sum > max) {
                                            result[0] = startCityId;
                                            result[1] = x;
                                            result[2] = y;
                                            result[3] = endCityId;
                                            max = sum;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    private boolean isDistinctCity(int a, int x, int y, int b) {
        if (a == x || a == y || a == b || x == y || x == b || y == b) {
            return false;
        }
        return true;
    }

    private void printAdjMatrix() {
        for (int i = 1; i < adj[1].length; i++) {
            for (int j = 1; j < adj.length; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printMax3To(City[][] threeFurthestTo) {
        for (int i = 1; i < threeFurthestTo.length; i++) {
            System.out.println("-----------Farthest city to reach" + i + "-------------------------");
            for (int j = 1; j < threeFurthestTo[i].length; j++) {
                System.out.print(threeFurthestTo[i][j].cityId + " ");
            }
            System.out.println();
            System.out.println("------------------------------------");
        }
    }

    private void printMax3From(City[][] threeFurthestFrom) {
        for (int i = 1; i < threeFurthestFrom.length; i++) {
            System.out.println("-----------City Farthest from" + i + "-------------------------");
            for (int j = 1; j < threeFurthestFrom[i].length; j++) {
                System.out.print(threeFurthestFrom[i][j].cityId + " ");
            }
            System.out.println();
            System.out.println("------------------------------------");
        }
    }

    public static void main(String[] args) {
        WorldTour wt = new WorldTour();
        wt.compute();
    }
}
