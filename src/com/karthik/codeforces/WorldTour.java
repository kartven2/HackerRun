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

    static class Path implements Comparable {

        private int[] vertex;
        private int pathCost;

        public Path(int[] vertex, int pathCost) {
            this.vertex = vertex;
            this.pathCost = pathCost;
        }

        @Override
        public int compareTo(Object that) {
            return this.pathCost - ((Path) that).pathCost;
        }
    }

    static class Edge {

        protected int from;
        protected int to;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Edge{" + "from=" + from + ", to=" + to + '}';
        }
    }

    static class WeightedEdge extends Edge {

        private int weight;

        public WeightedEdge(int from, int to, int weight) {
            super(from, to);
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
        private Edge maxEdge;

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

            int maxWeight = -1;
            for (int v = 1; v <= G.totalVertices; v++) {
                adj[s][v] = distTo[v] == MAX ? -1 : distTo[v];
                if (adj[s][v] > maxWeight) {
                    maxEdge = new Edge(s, v);
                    maxWeight = adj[s][v];
                }
            }
        }
    }

    private Edge maxEdgeInGraph;
    private int[][] adj;

    private void updateMaxEdgeInGraph(Edge maxEdge) {
        if (maxEdge != null && adj[maxEdge.from][maxEdge.to] > adj[maxEdgeInGraph.from][maxEdgeInGraph.to]) {
            maxEdgeInGraph = maxEdge;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream(new File("./resources/worldtour3")));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        Graph G = new Graph(sc);
        maxEdgeInGraph = new Edge(1, 1);
        adj = new int[G.totalVertices + 1][G.totalVertices + 1];
        for (int v = 1; v <= G.totalVertices; v++) {
            Bfs bfs = new Bfs(G, v, adj);
            updateMaxEdgeInGraph(bfs.maxEdge);
        }
        printAdjMatrix();
        Path allPath[] = new Path[3];
        //option 1: x -> y -> maxEdgeFrom -> maxEdgeTo
        List<Integer> skip = new ArrayList<>();
        skip.add(maxEdgeInGraph.from);
        skip.add(maxEdgeInGraph.to);
        int y = findMaxFromVertex(maxEdgeInGraph.from, skip);
        skip.add(y);
        int x = findMaxFromVertex(y, skip);
        int option1Cost = adj[x][y] + adj[y][maxEdgeInGraph.from] + adj[maxEdgeInGraph.from][maxEdgeInGraph.to];
        int[] vertexInPath = {x, y, maxEdgeInGraph.from, maxEdgeInGraph.to};
        allPath[0] = new Path(vertexInPath, option1Cost);

        //option 2: y -> maxEdgeFrom -> maxEdgeTo -> x
        x = findMaxToVertex(maxEdgeInGraph.to, skip);
        int option2Cost = adj[y][maxEdgeInGraph.from] + adj[maxEdgeInGraph.from][maxEdgeInGraph.to] + adj[maxEdgeInGraph.to][x];
        vertexInPath = new int[]{y, maxEdgeInGraph.from, maxEdgeInGraph.to, x};
        allPath[1] = new Path(vertexInPath, option2Cost);

        //option 3: maxEdgeFrom -> maxEdgeTo -> x -> y
        skip = new ArrayList<>();
        skip.add(maxEdgeInGraph.from);
        skip.add(maxEdgeInGraph.to);
        skip.add(x);
        y = findMaxToVertex(x, skip);
        int option3Cost = adj[maxEdgeInGraph.from][maxEdgeInGraph.to] + adj[maxEdgeInGraph.to][x] + adj[x][y];
        vertexInPath = new int[]{maxEdgeInGraph.from, maxEdgeInGraph.to, x, y};
        allPath[2] = new Path(vertexInPath, option3Cost);

        Arrays.sort(allPath);
        for (int output : allPath[2].vertex) {
            System.out.print(output + " ");
        }
    }

    private int findMaxFromVertex(int toVertex, List<Integer> skipList) {
        int max = -2;
        int result = 1;
        for (int i = 1; i < adj.length; i++) {
            if (adj[i][toVertex] > max && !skipList.contains(i)) {
                max = adj[i][toVertex];
                result = i;
            }
        }
        return result;
    }

    private int findMaxToVertex(int fromVertex, List<Integer> skipList) {
        int max = -2;
        int result = 1;
        for (int i = 1; i < adj[fromVertex].length; i++) {
            if (adj[fromVertex][i] > max && !skipList.contains(i)) {
                max = adj[fromVertex][i];
                result = i;
            }
        }
        return result;
    }

    private void printAdjMatrix() {
        for (int i = 1; i < adj[1].length; i++) {
            for (int j = 1; j < adj.length; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WorldTour wt = new WorldTour();
        wt.compute();
    }
}
