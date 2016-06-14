/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/652/C
 *
 * Count the number of different intervals (x, y) (1 ≤ x ≤ y ≤ n) 
 * that do not contain any foe pairs.
 *
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastFoePairs {

    class InputReader {

        private static final int INPUT_KB = 1024;

        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
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

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/foepairs3"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] pos = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pos[sc.nextInt()] = i;
        }
        int[] intvlForEach = new int[n + 1];
        Arrays.fill(intvlForEach, n + 1);
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (pos[l] > pos[r]) {
                int tmp = l;
                l = r;
                r = tmp;
            }
            //Deductions for foe pairs.
            //Smallest right most interval for each left position.
            intvlForEach[pos[l]] = Math.min(intvlForEach[pos[l]], pos[r]);
        }
        long ans = 0;
        for (int i = n; i > 0; i--) {
            if (i < n) {
                //Propogating deductions for elements before interval.
                intvlForEach[i] = Math.min(intvlForEach[i], intvlForEach[i + 1]);
            }
            //Deductions complete
            //Count up all possible intervals for each element.
            ans += intvlForEach[i] - i;
        }
        System.out.println(ans);
    }

    public static void main(String... args) {
        FastFoePairs fp = new FastFoePairs();
        fp.compute();
    }
}
