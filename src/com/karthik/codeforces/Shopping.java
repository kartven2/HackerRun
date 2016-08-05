/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/665/B
 *
 * Print the only integer t — the total time needed for
 * Ayush to process all the orders.
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
public class Shopping {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/shopping2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        int[] pos = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            pos[sc.nextInt()] = i;
        }
        int sum = 0;
        while (n-- > 0) {
            for (int i = 0; i < m; i++) {
                int q = sc.nextInt();
                sum += pos[q];
                for (int w = 1; w < pos.length; w++) {
                    if (pos[w] < pos[q]) {
                        pos[w]++;
                    }
                }
                pos[q] = 1;
            }
        }
        System.out.print(sum);
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
        Shopping sh = new Shopping();
        sh.compute();
    }
}
