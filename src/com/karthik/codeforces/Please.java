/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/696/C
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
public class Please {

    private static final long MOD = (long) 1e9 + 7;

    private long exp(long a, long b) {
        long result = 1;
        for (; b > 0; b >>= 1) {
            if (b % 2 == 1) {
                result = ((result * a) % MOD);
            }
            a = ((a * a) % MOD);
        }
        return result;
    }

    private long mul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    private long div(long a, long b) {
        return mul(a, exp(b, MOD - 2));
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/please"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        boolean even = false;
        long total = 1;
        for (int i = 0; i < n; i++) {
            long input = sc.nextLong();
            total *= input % (MOD - 1);
            total %= (MOD - 1);
            even |= input % 2 == 0;
        }
        long denom = exp(2, total) * div(1, 2) % MOD;
        int x = even ? 1 : -1;
        long numer = (denom + x + MOD) % MOD;
        numer *= div(1, 3);
        numer %= MOD;
        System.out.println(numer + "/" + denom);
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

        long nextLong() {
            return Long.parseLong(readNext());
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        Please pl = new Please();
        pl.compute();
    }
}
