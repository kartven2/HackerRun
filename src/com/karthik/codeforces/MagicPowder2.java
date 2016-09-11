/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/670/D2
 *
 * Print the maximum number of cookies, which Apollinaria
 * will be able to bake using the ingredients that she has and the magic powder.
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
public class MagicPowder2 {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private int n, k;
    private int[] a, b;

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/magicpowder1"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        n = sc.nextInt();
        k = sc.nextInt();
        a = new int[n];
        b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        long lo = 0, hi = (int) 9e9, mid = 0, cnt = 0, ans = 0;
        boolean possible;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            cnt = 0;
            possible = true;
            for (int i = 0; i < n; i++) {
                if (a[i] * mid > b[i]) {
                    cnt += (a[i] * mid - b[i]);
                    if (cnt > k) {
                        hi = mid - 1;
                        possible = false;
                        break;
                    }
                }
            }
            if (possible) {
                ans = mid;
                lo = mid + 1;
            }
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
        MagicPowder2 mp2 = new MagicPowder2();
        mp2.compute();
    }
}
