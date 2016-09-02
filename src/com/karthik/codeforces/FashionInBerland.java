/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/691/A
 *
 * In the only line print the word "YES" if the jacket
 * is fastened in a right way. Otherwise print the word "NO".
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
public class FashionInBerland {

    private static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private static InputReader sc = new InputReader(System.in);

    private void compute() {
        int n = sc.nextInt();
        boolean hit0 = false;
        try {
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                if (a == 0 && !hit0) {
                    hit0 = true;
                } else if (a == 0) {
                    pw.print("NO");
                    return;
                }
            }
            if (n == 1) {
                hit0 = !hit0;
            }
            if (!hit0) {
                pw.print("NO");
                return;
            }
            pw.print("YES");
        } finally {
            pw.flush();
        }
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
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        FashionInBerland fib = new FashionInBerland();
        fib.compute();
    }
}
