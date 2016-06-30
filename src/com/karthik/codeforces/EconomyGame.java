/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/681/B
 *
 * Print "YES" (without quotes) if it's possible that Kolya spent
 * all of his initial n coins buying only houses, cars and computers. 
 * Otherwise print "NO" (without quotes). 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EconomyGame {

    private static final int a = 1234567;
    private static final int b = 123456;
    private static final int c = 1234;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/economygame"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        int n = sc.nextInt();
        for (int i = 0; (i * a) <= n && n > 0; i++) {
            for (int j = 0; (i * a) + (j * b) <= n; j++) {
                if ((n - (i * a) - (j * b)) % c == 0) {
                    System.out.println("YES");
                    System.exit(0);
                }
            }
        }
        System.out.println("NO");
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

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        EconomyGame eg = new EconomyGame();
        eg.compute();
    }
}
