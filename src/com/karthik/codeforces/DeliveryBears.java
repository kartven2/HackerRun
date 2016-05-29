/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/653/D
 *
 * Find the max flow in a directed network with some constratints.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
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
public class DeliveryBears {

    private boolean hasAugmentingPath() {
        marked = new boolean[n + 1];
        edgeTo = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        marked[1] = true;
        while (!queue.isEmpty() && !marked[n]) {
            int v = queue.remove();
            for (int w : adj[v]) {
                if (weight[v][w] > 0.0) {
                    if (!marked[w]) {
                        marked[w] = true;
                        edgeTo[w] = v;
                        queue.add(w);
                    }
                }
            }
        }
        return marked[n];
    }

    static class InputReader {

        private static final int inputkb = 5 * 1024;
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

        private double nextDouble() {
            return Double.parseDouble(readNext());
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private boolean[] marked;
    private int[] edgeTo;
    private double[][] weight;
    private List<Integer>[] adj;
    private int n;
    private int m;
    private int x;
    private int r;
    private double[] processedPath;
    private double totalWeight;
    private int bearsHi;
    private int bearsLo;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/deliverybears2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        weight = new double[n + 1][n + 1];
        adj = (List<Integer>[]) new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            weight[from][to] = sc.nextDouble();
            adj[from].add(to);
        }
        processedPath = new double[n + 1];
        while (hasAugmentingPath()) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = n; v != 1; v = edgeTo[v]) {
                bottle = Math.min(bottle, weight[edgeTo[v]][v]);
            }
            for (int v = n; v != 1; v = edgeTo[v]) {
                weight[edgeTo[v]][v] -= bottle;
            }
            totalWeight += bottle;
            processedPath[r++] = bottle;

        }
        if (totalWeight >= x) {
            System.out.println(String.format("%.10f", totalWeight));
            return;
        }
        Arrays.sort(processedPath, 0, r);
        double optimalWeight = totalWeight / x;
        int remainingBears = x;
        for (int i = r - 1; i >= 0; i--) {
            double bearCount = processedPath[i] / optimalWeight;
            int bearCountInt = (int) Math.round(bearCount);
            if (i == r - 1) {
                bearsHi = bearCountInt;
            } else if (i == 0) {
                bearsLo = bearCountInt;
            }

            remainingBears -= bearCountInt;
        }
        if (remainingBears > 0) {
            bearsHi += remainingBears;
        }
        System.out.println(String.format("%.10f", Math.min((processedPath[r - 1] / bearsHi), (processedPath[0] / bearsLo)) * x));
    }

    public static void main(String[] args) {
        DeliveryBears db = new DeliveryBears();
        db.compute();
    }
}
