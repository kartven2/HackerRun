/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/689/B
 *
 * In the only line print n integers m1, m2, ..., mn,
 * where mi denotes the least amount of total energy required
 * to walk from intersection 1 to intersection i.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MikeAndShortcuts {

    private int[] dp;
    private int[] s;
    private Queue<Integer> q;
    private int n;

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
        n = sc.nextInt();
        dp = new int[n + 1];
        s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
        }
        q = new LinkedList<>();
        visit(1, 1);
        while (!q.isEmpty()) {
            int v = q.remove();
            visit(s[v], dp[v] + 1);
            if (v > 1) {
                visit(v - 1, dp[v] + 1);
            }
            if (v < n) {
                visit(v + 1, dp[v] + 1);
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(dp[i] - 1 + " ");
        }
    }

    void visit(int node, int dist) {
        if (dp[node] == 0) {
            dp[node] = dist;
            q.add(node);
        }
    }

    public static void main(String... args) {
        MikeAndShortcuts ms = new MikeAndShortcuts();
        ms.compute();
    }
}
