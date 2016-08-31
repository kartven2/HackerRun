/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/701/C
 *
 * Print the minimum number of flats which Sergei B.
 * should visit in order to catch Pokemons of all types
 * which there are in the house.
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
public class TheyAreEverywhere {

    private static final int MAX = Integer.MAX_VALUE;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/theyareeverywhere"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        String ip = sc.readNext();
        boolean[] ch = new boolean[52];
        int uniquech = 0;
        char[] unchs = new char[52];
        for (int i = 0; i < n; i++) {
            char c = ip.charAt(i);
            int idx = (c - 'a') >= 0 ? (26 + (c - 'a')) : (c - 'A');
            if (!ch[idx]) {
                unchs[uniquech++] = c;
                ch[idx] = true;
            }
        }
        int[] len = new int[n];
        for (int i = 0; i < uniquech; i++) {
            int last = -1;
            for (int j = 0; j < n; j++) {
                if (ip.charAt(j) == unchs[i]) {
                    last = j;
                }
                len[j] = last == -1 ? MAX : Math.max(len[j], j - last + 1);
            }
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, len[i]);
        }
        System.out.print(ans);
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
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
        TheyAreEverywhere tae = new TheyAreEverywhere();
        tae.compute();
    }
}
