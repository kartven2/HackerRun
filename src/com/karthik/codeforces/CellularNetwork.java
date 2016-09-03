/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/702/C
 *
 * Print minimal r so that each city will be covered by cellular network. 
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
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CellularNetwork {

    private static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    //private static InputReader sc = new InputReader(System.in);
    private static InputReader sc = null;

    static {
        try {
            sc = new InputReader(new FileInputStream("./resources/cellularnetwork"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private int bsearch(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while (hi >= lo) {
            int mid = (lo + hi) >> 1;
            if (a[mid] == k) {
                return mid;
            } else if (a[mid] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private int computeDistance(int[] a, int k) {
        int ki = bsearch(a, k), m = a.length - 1;
        if (ki > m) {
            return Math.abs(k - a[m]);
        }
        if (a[ki] == k) {
            return 0;
        }
        if (ki == 0) {
            return Math.abs(k - a[ki]);
        }
        return Math.min(Math.abs(k - a[ki]), Math.abs(k - a[ki - 1]));
    }

    private void compute() {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] c = new int[n];
        int[] t = new int[m];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            t[i] = sc.nextInt();
        }
        int dist = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dist = Math.max(dist, computeDistance(t, c[i]));
        }
        pw.print(dist);
        pw.flush();
    }

    static class InputReader {

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
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        CellularNetwork cn = new CellularNetwork();
        cn.compute();
    }
}
