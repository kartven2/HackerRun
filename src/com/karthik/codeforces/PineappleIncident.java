/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/697/A
 *
 * Print a single "YES" (without quotes) if the
 * pineapple will bark at time x or a single "NO"
 * (without quotes) otherwise in the only line of output.
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
public class PineappleIncident {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/pineapple"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int t = sc.nextInt(), s = sc.nextInt(), x = sc.nextInt();
        if (x < t) {
            System.out.print("NO");
        } else if (x == t) {
            System.out.print("YES");
        } else if (x < (t + s)) {
            System.out.print("NO");
        } else {
            x -= t;
            String ans = x % s <= 1 ? "YES" : "NO";
            System.out.print(ans);
        }
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
        PineappleIncident cf = new PineappleIncident();
        cf.compute();
    }
}
