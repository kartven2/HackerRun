/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/670/D1
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
public class MagicPowder1 {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/magicpowder1"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt(), k = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        long ans = 0;
        for (boolean bake = true; bake; ans++) {
            for (int i = 0; i < n; i++) {
                if (b[i] >= a[i]) {
                    b[i] -= a[i];
                } else if (b[i] < a[i] && k >= a[i] - b[i]) {
                    k -= (a[i] - b[i]);
                    b[i] = 0;
                } else {
                    bake = false;
                    break;
                }
            }
        }
        pw.print(ans - 1);
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
        MagicPowder1 mp1 = new MagicPowder1();
        mp1.compute();
    }

}
