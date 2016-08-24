/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/690/F1
 *
 * Print one integer – the number of lifelines in the tree.
 * 
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
public class TreeOfLife {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc;
        try {
            sc = new InputReader(new FileInputStream("./resources/treeoflife"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int[] degree = new int[n];
        for (int i = 0; i < (2 * n - 2); i++) {
            degree[sc.nextInt() - 1]++;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (degree[i] * (degree[i] - 1)) >> 1;
        }
        System.out.print(ans);
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
        TreeOfLife tl = new TreeOfLife();
        tl.compute();
    }
}
