/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/678/C
 *
 * Print the only integer s — the maximum number of chocolates Joty can get.
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
public class JotyChocolate {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/jotychoclate"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        long[] input = new long[5];
        for (int i = 0; i < 5; i++) {
            input[i] = sc.nextLong();
        }
        long sum = input[3] * (input[0] / input[1]) + input[4] * (input[0] / input[2]);
        long lcm = lcm(input[1], input[2]);
        long insc = input[0] / lcm;
        long option1 = sum - (insc * input[3]);
        long option2 = sum - (insc * input[4]);
        System.out.println(Math.max(option1, option2));
    }

    private long lcm(long x, long y) {
        return x * (y / gcd(x, y));
    }

    private long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
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

        private long nextLong() {
            return Long.parseLong(readNext());
        }
    }

    public static void main(String... args) {
        JotyChocolate jc = new JotyChocolate();
        jc.compute();
    }

}
