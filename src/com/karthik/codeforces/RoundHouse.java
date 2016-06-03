/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/659/A
 *
 * Find the number of the entrance, near which he will be at the end of his walk.
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
public class RoundHouse {

    class InputReader {

        private static final int INPUT_KB = 5 * 1024;
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

    private int n;
    private int a;
    private int b;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/roundhouse"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        int absb = b < 0 ? -b : b;
        int bmodn = absb % n;
        bmodn = b < 0 ? -bmodn : bmodn;
        int ans = a + bmodn;
        ans = ans <= 0 ? n + ans : ans > n ? ans % n : ans;
        System.out.println(ans);
    }

    public static void main(String[] args) {
        RoundHouse r = new RoundHouse();
        r.compute();
    }
}
