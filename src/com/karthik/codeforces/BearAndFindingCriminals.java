/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/680/B
 *
 * Print the number of criminals Limak will catch.
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
public class BearAndFindingCriminals {

    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private InputReader sc = new InputReader(System.in);

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/bearandfindingcriminals"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        int n = sc.nextInt(), a = sc.nextInt(), i = a - 2, j = a;
        int[] c = new int[n];
        for (int k = 0; k < n; k++) {
            c[k] = sc.nextInt();
        }
        long ans = c[a - 1];
        for (;;) {
            if (i < 0 && j > n - 1) {
                break;
            } else if (i < 0) {
                if (c[j++] == 1) {
                    ans++;
                }
            } else if (j > n - 1) {
                if (c[i--] == 1) {
                    ans++;
                }
            } else if (c[i--] == 1 & c[j++] == 1) {
                ans += 2;
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
        BearAndFindingCriminals bfc = new BearAndFindingCriminals();
        bfc.compute();
    }

}
