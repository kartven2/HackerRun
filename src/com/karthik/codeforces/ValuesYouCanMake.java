/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/688/E
 *
 * First line of the output must contain a single integer
 * q— the number of suitable values x. Then print q integers
 * in ascending order — the values that Arya can make for some
 * subset of coins of Pari that pays for the chocolate.
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
public class ValuesYouCanMake {

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

        long nextLong() {
            return Long.parseLong(readNext());
        }
    }

    void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/valuesyoucanmake2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[][] table = new boolean[k + 1][k + 1];
        table[0][0] = true;
        for (int i = 0; i < n; i++) {
            int c = sc.nextInt();
            for (int j = k - c; j >= 0; j--) {
                for (int l = 0; l <= j; l++) {
                    if (table[j][l]) {
                        table[j + c][l] = table[j + c][l + c] = true;
                    }
                }
            }
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            if (table[k][i]) {
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count);
        System.out.println(sb.toString());
    }

    public static void main(String... args) {
        ValuesYouCanMake vcm = new ValuesYouCanMake();
        vcm.compute();
    }
}
