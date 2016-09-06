/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/682/A
 *
 * Print the only integer — the number of pairs of
 * integers (x, y) such that 1 ≤ x ≤ n, 1 ≤ y ≤ m and (x + y) is divisible by 5.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
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
public class AlyonaAndNumbers {

    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private InputReader sc = new InputReader(System.in);

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

    private void compute() {
        int n = sc.nextInt(), m = sc.nextInt();
        if (n > m) {
            int t = n;
            n = m;
            m = t;
        }
        int[] pre = new int[5];
        for (int i = 1; i <= m; i++) {
            pre[i % 5]++;
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += pre[(5 - i % 5) % 5];
        }
        pw.print(ans);
        pw.flush();
    }

    public static void main(String... args) {
        AlyonaAndNumbers an = new AlyonaAndNumbers();
        an.compute();
    }
}
