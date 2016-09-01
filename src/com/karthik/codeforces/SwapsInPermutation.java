/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/691/D
 *
 * Print the only line with n distinct integers p'i (1 ≤ p'i ≤ n)
 * — the lexicographically maximal permutation one can get.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SwapsInPermutation {

    private static final PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    //private static final InputReader sc = new InputReader(System.in);
    private static InputReader sc = null;

    static {
        try {
            sc = new InputReader(new FileInputStream("./resources/swapsinpermutation"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private static class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private int n, m, ee, pos, v, w;
    private int[] a, oi, o, vis, ans, head, edge, next;

    private void addEdge(int x, int y) {
        edge[ee] = y;
        next[ee] = head[x];
        head[x] = ee++;
    }

    private void dfs(int i) {
        vis[i] = 1;
        oi[pos] = i;
        o[pos++] = a[i];
        for (int x = head[i]; x != -1; x = next[x]) {
            int y = edge[x];
            if (vis[y] == 0) {
                dfs(y);
            }
        }
    }

    private void compute() {
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n + 1];
        oi = new int[n + 1];
        o = new int[n + 1];
        vis = new int[n + 1];
        ans = new int[n + 1];
        head = new int[n + 1];
        edge = new int[(int) 2e5 + 9];
        next = new int[(int) 2e5 + 9];
        Arrays.fill(head, -1);
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int j = 1; j <= m; j++) {
            v = sc.nextInt();
            w = sc.nextInt();
            addEdge(v, w);
            addEdge(w, v);
        }
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                pos = 0;
                dfs(i);
                asort(oi, 0, pos - 1);
                asort(o, 0, pos - 1);
                for (int k = 0; k < pos; k++) {
                    ans[oi[k]] = o[pos - 1 - k];
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            pw.print(ans[i] + " ");
        }
        pw.flush();
    }

    private void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void asort(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, gt = hi, i = lo, v = a[lo];
        while (i <= gt) {
            if (a[i] < v) {
                exchange(a, i++, lt++);
            } else if (a[i] > v) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        asort(a, lo, lt - 1);
        asort(a, gt + 1, hi);
    }

    public static void main(String... args) {
        SwapsInPermutation sip = new SwapsInPermutation();
        sip.compute();
    }
}
