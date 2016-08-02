/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/678/D
 *
 * Print the only integer s — the value g(n)(x) modulo 10^9 + 7.
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
public class IteratedLinearFunction {

    class Arithmetic {

        private static final long MOD = (long) 1e9 + 7;

        private long add(long a, long b) {
            return ((a % MOD) + (b % MOD)) % MOD;
        }

        private long sub(long a, long b) {
            return (((a % MOD) - (b % MOD)) + MOD) % MOD;
        }

        private long mul(long a, long b) {
            return ((a % MOD) * (b % MOD)) % MOD;
        }

        private long div(long a, long b) {
            return mul(a, pow(b, MOD - 2));
        }

        private long pow(long a, long b) {
            long result = 1;
            for (; b > 0; b >>= 1) {
                if (b % 2 == 1) {
                    result = mul(result, a);
                }
                a = mul(a, a);
            }
            return result;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/iteratedlinearfunction"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        long a = sc.nextLong(), b = sc.nextLong(), n = sc.nextLong(), x = sc.nextLong();
        Arithmetic c = new Arithmetic();
        //a^n*x+b*(1-a^n)/(1-a);
        long apown = c.pow(a, n);
        long term1 = c.mul(apown, x);
        long term2 = a == 1 ? n : c.div(c.sub(1, apown), c.sub(1, a));
        System.out.print(c.add(term1, c.mul(term2, b)));
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
    }

    public static void main(String... args) {
        IteratedLinearFunction ilf = new IteratedLinearFunction();
        ilf.compute();
    }
}
