/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/677/D
 *
 * Find the minimum possible total distance Vanya
 * has to walk in order to get the treasure from the chest of type p.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class VanyaAndTreasure {

    private static final int MAX = Integer.MAX_VALUE;

    class Node implements Comparable {

        private int x;
        private int y;
        private int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Node{" + "x=" + x + ", y=" + y + ", d=" + d + '}';
        }

        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            return this.d - that.d;
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

        private Integer nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private Node[][] dp;
    private int[] sizeof;
    private int n;
    private int m;
    private int p;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/vanyantreasure3"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        dp = new Node[(n * m) + 1][(n * m) + 1];
        sizeof = new int[(n * m) + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int type = sc.nextInt();
                int d = type == 1 ? (i + j) : MAX;
                dp[type][sizeof[type]++] = new Node(i, j, d);
            }
        }

        for (int i = 2; i <= p; i++) {
            Arrays.sort(dp[i], 0, sizeof[i]);
            int limit = Math.min((n + m), sizeof[i - 1]);
            for (int j = 0; j < sizeof[i]; j++) {
                for (int k = 0; k < limit; k++) {
                    dp[i][j].d = Math.min(dp[i][j].d, dp[i - 1][k].d + dist(i - 1, k, i, j));
                }
            }
        }

        //printArray();
        int result = MAX;
        for (int i = 0; i < sizeof[p]; i++) {
            result = Math.min(result, dp[p][i].d);
        }
        System.out.println(result);
    }

    private int dist(int a, int b, int c, int d) {
        return Math.abs(dp[a][b].x - dp[c][d].x) + Math.abs(dp[a][b].y - dp[c][d].y);
    }

    private void printArray() {
        for (int i = 0; i <= p; i++) {
            for (int j = 0; j < sizeof[i]; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String... args) {
        VanyaAndTreasure t = new VanyaAndTreasure();
        t.compute();
    }
}
