/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/703/B
 *
 * Print the only integer — summary price of passing each of the roads in XXX.
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
public class MishkaAndTrip {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/mishkaandtrip"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt(), k = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        boolean[] c = new boolean[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        for (int i = 0; i < k; i++) {
            int pi = sc.nextInt() - 1;
            b[i] = pi;
            c[pi] = true;
        }
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int ci = a[b[i]];
            ans += ci * (sum - ci);
            sum -= ci;
        }
        for (int i = 1; i < n; i++) {
            if (!c[i] && !c[i - 1]) {
                ans += (a[i] * a[i - 1]);
            }
        }
        if (!c[0] && !c[n - 1]) {
            ans += (a[0] * a[n - 1]);
        }
        pw.print(ans);
        pw.flush();

    }

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
        MishkaAndTrip mat = new MishkaAndTrip();
        mat.compute();
    }

}
