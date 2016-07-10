/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/685/B
 *
 * For each query print the index of a centroid of the corresponding subtree.
 * If there are many suitable nodes, print any of them. It's guaranteed, that
 * each subtree has at least one centroid.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KayAndSnowflake {

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

    private InputReader sc;
    private int n, q;
    private int[] parent, sz, centroid;
    private LinkedList<Integer>[] children;

    private void compute() {
        //sc = new InputReader(System.in);
        try {
            sc = new InputReader(new FileInputStream("./resources/kayandsnowflake"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        q = sc.nextInt();
        parent = new int[n];
        children = (LinkedList<Integer>[]) new LinkedList[n];
        sz = new int[n];
        centroid = new int[n];
        int j = 0;
        parent[j] = -1;
        while (j < n) {
            children[j++] = new LinkedList<>();
        }
        j = 1;
        while (j < n) {
            parent[j] = sc.nextInt() - 1;
            children[parent[j]].add(j);
            j++;
        }
        findCentroid(0);
        while (q-- > 0) {
            System.out.println(centroid[sc.nextInt() - 1] + 1);
        }
    }

    private int findCentroid(int v) {
        sz[v] = 1;
        for (int child : children[v]) {
            sz[v] += findCentroid(child);
        }
        centroid[v] = v;
        for (int child : children[v]) {
            if (2 * sz[child] >= sz[v]) {
                int currentCentroid = centroid[child];
                while (2 * sz[currentCentroid] < sz[v]) {
                    currentCentroid = parent[currentCentroid];
                }
                centroid[v] = currentCentroid;
                break;
            }
        }
        return sz[v];
    }

    public static void main(String... args) {
        KayAndSnowflake ksf = new KayAndSnowflake();
        ksf.compute();
    }
}
